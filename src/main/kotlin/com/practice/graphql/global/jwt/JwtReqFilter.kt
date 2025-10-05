package com.practice.graphql.global.jwt

import com.practice.graphql.global.exception.collections.AccessTokenExpiredException
import com.practice.graphql.global.exception.collections.TokenNotValidException
import com.practice.graphql.global.config.security.auth.AuthDetailService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.InvalidClaimException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtReqFilter(
    val tokenProvider: TokenProvider,
    val authDetailService: AuthDetailService,
): OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val accessToken = request.getHeader("Authorization")
        if(accessToken != null) {
            try {
                val tokenType = tokenProvider.getTokenType(accessToken)
                if (tokenType != "accessToken") {
                    throw TokenNotValidException()
                }
                registerSecurityContext(request, accessToken)
            } catch (_: ExpiredJwtException) {
                throw AccessTokenExpiredException()
            } catch (_: InvalidClaimException) {
                throw TokenNotValidException()
            }
        }
        filterChain.doFilter(request, response)
    }

    fun registerSecurityContext(request: HttpServletRequest, accessToken: String){
        val userDetail = authDetailService.loadUserByUsername(tokenProvider.getUserEmail(accessToken))
        val authenticationToken = UsernamePasswordAuthenticationToken(userDetail, null, userDetail.authorities)
        authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authenticationToken
    }
}