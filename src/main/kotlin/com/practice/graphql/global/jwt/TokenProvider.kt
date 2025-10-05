package com.practice.graphql.global.jwt

import com.practice.graphql.domain.member.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date

@Component
class TokenProvider {
    private val accessExpireTime: Long = 1000 * 60 * 60 * 3
    private val refreshExpireTime: Long= accessExpireTime/3 * 24 * 30 * 6
    @Value("\${jwt.access-secret}")
    private val accessSecret: String = ""
    @Value("\${jwt.refresh-secret}")
    private val refreshSecret :String = ""

    private enum class TokenType(val value: String){
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken")
    }
    private enum class TokenClaimName(val value: String){
        USER_EMAIL("userEmail"),
        TOKEN_TYPE("tokenType"),
        ROLES("roles")
    }

    private fun getSignInKey(secretKey: String): Key{
        val bytes = secretKey.toByteArray(StandardCharsets.UTF_8)
        return Keys.hmacShaKeyFor(bytes)
    }

    private fun extractAccessClaims(token: String): Claims{
        val tokenR = token.replace("Bearer ", "")
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey(accessSecret))
            .build()
            .parseClaimsJws(tokenR)
            .body
    }

    private fun extractRefreshToken(token: String): Claims{
        val tokenR = token.replace("Bearer ", "")
        return Jwts.parserBuilder()
            .setSigningKey(getSignInKey(refreshSecret))
            .build()
            .parseClaimsJws(tokenR)
            .body
    }

    fun getUserEmail(token: String): String = extractAccessClaims(token).get(TokenClaimName.USER_EMAIL.value, String::class.java)

    fun getTokenType(token: String): String = extractAccessClaims(token).get(TokenClaimName.TOKEN_TYPE.value, String::class.java)

    private fun createToken(type: TokenType, email: String, expiredTime: Long, secretKey: String, claims: Claims): String{
        claims[TokenClaimName.USER_EMAIL.value] = email
        claims[TokenClaimName.TOKEN_TYPE.value] = type.value
        return Jwts.builder()
            .addClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiredTime))
            .signWith(getSignInKey(secretKey), SignatureAlgorithm.HS256)
            .compact()
    }

    fun createAccessToken(email: String, roles: List<Role>): String{
        val claims = Jwts.claims()
        claims[TokenClaimName.ROLES.value] = roles
        return createToken(TokenType.ACCESS_TOKEN, email, accessExpireTime, accessSecret, claims)
    }
    fun createRefreshToken(email: String): String {
        return createToken(TokenType.REFRESH_TOKEN, email, refreshExpireTime, refreshSecret, Jwts.claims())
    }
}