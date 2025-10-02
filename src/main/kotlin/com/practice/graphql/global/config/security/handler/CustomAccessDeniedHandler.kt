package com.practice.graphql.global.config.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {
    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)
    @Throws(IOException::class)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        log.error("==========Access Denied==========")
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}