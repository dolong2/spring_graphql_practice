package com.practice.graphql.global.jwt.property

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import java.nio.charset.StandardCharsets

@ConfigurationProperties(prefix = "jwt")
class JwtProperty(
    accessSecret: String,
    refreshSecret: String,
    val accessExpireTime: Long,
    val refreshExpireTime: Long,
) {
    val accessSecret = Keys.hmacShaKeyFor(accessSecret.toByteArray(StandardCharsets.UTF_8))
    val refreshSecret = Keys.hmacShaKeyFor(refreshSecret.toByteArray(StandardCharsets.UTF_8))
}