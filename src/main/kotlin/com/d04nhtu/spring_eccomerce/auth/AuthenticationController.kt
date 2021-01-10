package com.d04nhtu.spring_eccomerce.auth

import com.d04nhtu.spring_eccomerce.security.CustomUserDetailsService
import com.d04nhtu.spring_eccomerce.security.JwtUtil
import io.jsonwebtoken.impl.DefaultClaims
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@RestController
class AuthenticationController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userDetailsService: CustomUserDetailsService

    @Autowired
    lateinit var jwtUtil: JwtUtil

    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*>? {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    authenticationRequest.username, authenticationRequest.password
                )
            )
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }

        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val token = jwtUtil.generateToken(userDetails)
        return ResponseEntity.ok(AuthenticationResponse(token))
    }

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun saveUser(@RequestBody req: CreateUserRequest): ResponseEntity<*>? {
        return if (userDetailsService.existsByUsername(req.username)) {
            ResponseEntity.badRequest().body("Username exists!")
        } else {
            ResponseEntity.ok(userDetailsService.save(req))
        }
    }

    @RequestMapping(value = ["/refreshtoken"], method = [RequestMethod.GET])
    @Throws(Exception::class)
    fun refreshToken(request: HttpServletRequest): ResponseEntity<*>? {
        // From the HttpRequest get the claims
        val claims = request.getAttribute("claims") as DefaultClaims
        val expectedMap = getMapFromIoJsonwebtokenClaims(claims)
        val token: String = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap["sub"].toString())
        return ResponseEntity.ok<Any>(AuthenticationResponse(token))
    }

    fun getMapFromIoJsonwebtokenClaims(claims: DefaultClaims): Map<String, Any> {
        val expectedMap: MutableMap<String, Any> = HashMap()
        for ((key, value) in claims) {
            expectedMap[key] = value
        }
        return expectedMap
    }
}