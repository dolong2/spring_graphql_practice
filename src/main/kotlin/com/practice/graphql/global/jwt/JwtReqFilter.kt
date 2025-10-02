package com.practice.graphql.global.jwt

import com.practice.graphql.global.exception.collections.AccessTokenExpiredException
import com.practice.graphql.global.exception.collections.TokenNotValidException
import com.practice.graphql.global.config.security.auth.AuthDetailService
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
        if(accessToken!=null){
            if(tokenProvider.isTokenExpired(accessToken)){
                throw AccessTokenExpiredException()//토큰만료
            }else if(!tokenProvider.getTokenType(accessToken).equals("accessToken")){
                throw TokenNotValidException()
            }
            registerSecurityContext(request, accessToken)
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