package com.d04nhtu.spring_eccomerce.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtUtil(
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.expirationDateInMs}")
    private val jwtExpirationInMs: Int,
    @Value("\${jwt.refreshExpirationDateInMs}")
    private val refreshExpirationDateInMs: Int
) {
    // generate token for user
    fun generateToken(userDetails: UserDetails): String {
        val claims: MutableMap<String, Any> = HashMap()
        val roles = userDetails.authorities
        for (role in roles) {
            if (role.authority == "ROLE_ADMIN") {
                claims["isAdmin"] = true
            }
            if (role.authority == "ROLE_USER") {
                claims["isUser"] = true
            }
        }
        return doGenerateToken(claims, userDetails.username)
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtExpirationInMs))
            .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    fun doGenerateRefreshToken(claims: Map<String, Any>, subject: String?): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + refreshExpirationDateInMs))
            .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    fun validateToken(authToken: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken)
            true
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getUsernameFromToken(token: String?): String {
        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
        return claims.subject
    }

    fun getRolesFromToken(authToken: String?): List<SimpleGrantedAuthority>? {
        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken).body
        val isAdmin = claims["isAdmin"]
        val isUser = claims["isUser"]
        if (isAdmin != null && isAdmin == true) {
            return listOf(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
        if (isUser != null && isUser == true) {
            return listOf(SimpleGrantedAuthority("ROLE_USER"))
        }
        return null
    }
}