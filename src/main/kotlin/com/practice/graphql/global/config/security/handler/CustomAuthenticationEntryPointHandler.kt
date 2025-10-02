package com.practice.graphql.global.config.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPointHandler: AuthenticationEntryPoint {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        log.error("==========AuthenticationEntryPoint==========")
        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}