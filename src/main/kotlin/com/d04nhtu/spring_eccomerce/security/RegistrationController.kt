package com.d04nhtu.spring_eccomerce.security

import com.d04nhtu.spring_eccomerce.models.User
import com.d04nhtu.spring_eccomerce.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping(path = ["/register"], produces = ["application/json"])
class RegistrationController(val userRepo: UserRepository,
                             val passwordEncoder: PasswordEncoder) {

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun processRegistration(@RequestBody user: User): User {
        user.password = passwordEncoder.encode(user.password)
        return userRepo.save(user)
    }
}