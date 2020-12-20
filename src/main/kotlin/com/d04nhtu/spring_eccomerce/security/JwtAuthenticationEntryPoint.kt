package com.d04nhtu.spring_eccomerce.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.*
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest, response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        val exception = request.getAttribute("exception")
        val message: String?
        if (exception != null) {
            val body = ObjectMapper().writeValueAsBytes(Collections.singletonMap("cause", exception.toString()))
            response.outputStream.write(body)
        } else {
            message = if (authException.cause != null) {
                authException.cause.toString() + " " + authException.message
            } else {
                authException.message
            }
            val body = ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message))
            response.outputStream.write(body)
        }
    }
}