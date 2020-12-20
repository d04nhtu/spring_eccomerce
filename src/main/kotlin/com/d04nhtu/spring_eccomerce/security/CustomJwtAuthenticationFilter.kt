package com.d04nhtu.spring_eccomerce.security

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomJwtAuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    lateinit var jwtTokenUtil: JwtUtil

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
            val jwtToken: String? = extractJwtFromRequest(request)

            if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
                val userDetails: UserDetails = User(
                    jwtTokenUtil.getUsernameFromToken(jwtToken), "",
                    jwtTokenUtil.getRolesFromToken(jwtToken)
                )
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                // After setting the Authentication in the context, we specify that the current user is authenticated.
                // So it passes the Spring Security Configurations successfully.
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            } else {
                println("Cannot set the Security Context")
            }
        } catch (ex: ExpiredJwtException) {
            val isRefreshToken = request.getHeader("isRefreshToken")
            val requestURL = request.requestURL.toString()
            // allow for Refresh Token creation if following conditions are true.
            if (isRefreshToken != null && isRefreshToken == "true" && requestURL.contains("refreshtoken")) {
                allowForRefreshToken(ex, request)
            } else request.setAttribute("exception", ex)
        } catch (ex: BadCredentialsException) {
            request.setAttribute("exception", ex)
        }
        filterChain.doFilter(request, response)
    }

    private fun allowForRefreshToken(ex: ExpiredJwtException, request: HttpServletRequest) {
        // create a UsernamePasswordAuthenticationToken with null values.
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
            null, null, null
        )
        // After setting the Authentication in the context, we specify
        // that the current user is authenticated. So it passes the
        // Spring Security Configurations successfully.
        SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
        // Set the claims so that in controller we will be using it to create
        // new JWT
        request.setAttribute("claims", ex.claims)
    }

    private fun extractJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }
}
