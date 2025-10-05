package com.practice.graphql.global.jwt

import com.practice.graphql.domain.member.Role
import com.practice.graphql.global.jwt.property.JwtProperty
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
class TokenProvider(
    private val jwtProperty: JwtProperty
) {
    private enum class TokenType(val value: String){
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken")
    }
    private enum class TokenClaimName(val value: String){
        USER_EMAIL("userEmail"),
        TOKEN_TYPE("tokenType"),
        ROLES("roles")
    }

    private fun extractAccessClaims(token: String): Claims{
        val tokenR = token.replace("Bearer ", "")
        return Jwts.parserBuilder()
            .setSigningKey(jwtProperty.accessSecret)
            .build()
            .parseClaimsJws(tokenR)
            .body
    }

    private fun extractRefreshToken(token: String): Claims{
        val tokenR = token.replace("Bearer ", "")
        return Jwts.parserBuilder()
            .setSigningKey(jwtProperty.refreshSecret)
            .build()
            .parseClaimsJws(tokenR)
            .body
    }

    fun getUserEmail(token: String): String = extractAccessClaims(token).get(TokenClaimName.USER_EMAIL.value, String::class.java)

    fun getTokenType(token: String): String = extractAccessClaims(token).get(TokenClaimName.TOKEN_TYPE.value, String::class.java)

    private fun createToken(type: TokenType, email: String, expiredTime: Long, secretKey: Key, claims: Claims): String{
        claims[TokenClaimName.USER_EMAIL.value] = email
        claims[TokenClaimName.TOKEN_TYPE.value] = type.value
        return Jwts.builder()
            .addClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiredTime))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun createAccessToken(email: String, roles: List<Role>): String{
        val claims = Jwts.claims()
        claims[TokenClaimName.ROLES.value] = roles
        return createToken(TokenType.ACCESS_TOKEN, email, jwtProperty.accessExpireTime, jwtProperty.accessSecret, claims)
    }
    fun createRefreshToken(email: String): String {
        return createToken(TokenType.REFRESH_TOKEN, email, jwtProperty.refreshExpireTime, jwtProperty.refreshSecret, Jwts.claims())
    }
}