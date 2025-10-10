package com.practice.graphql.global.config.security

import com.practice.graphql.global.config.security.handler.CustomAccessDeniedHandler
import com.practice.graphql.global.config.security.handler.CustomAuthenticationEntryPointHandler
import com.practice.graphql.global.jwt.JwtReqFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val jwtRequestFilter: JwtReqFilter,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { } // CORS 기본 설정 활성화
            .csrf { it.disable() } // CSRF 비활성화
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth ->
                auth
                    // Swagger / H2 console 화이트리스트
                    .requestMatchers(
                        "/v1/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/configuration/**",
                        "/webjars/**",
                        "/public",
                        "/h2-console/**"
                    ).permitAll()

                    // GraphQL endpoint
                    .requestMatchers(HttpMethod.POST, "/graphql").permitAll()
                    .requestMatchers(HttpMethod.GET, "/graphql").permitAll()

                    // 그 외는 인증 필요
                    .anyRequest().authenticated()
            }
            .exceptionHandling { ex ->
                ex.accessDeniedHandler(CustomAccessDeniedHandler())
                ex.authenticationEntryPoint(CustomAuthenticationEntryPointHandler())
            }
            // 필터 추가
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)

        // H2 console 사용 시 frameOptions 비활성화
        http.headers { headers ->
            headers.frameOptions { it.disable() }
        }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(12)
}