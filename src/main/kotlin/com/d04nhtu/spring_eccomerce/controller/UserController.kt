package com.d04nhtu.spring_eccomerce.controller

import com.d04nhtu.spring_eccomerce.models.DAOUser
import com.d04nhtu.spring_eccomerce.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/user")
class UserController(val userRepo: UserRepository) {
    @GetMapping
    fun currentUser(principal: Principal): DAOUser? = userRepo.findByUsername(principal.name)
}
