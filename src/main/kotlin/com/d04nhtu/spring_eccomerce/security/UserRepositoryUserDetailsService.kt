package com.d04nhtu.spring_eccomerce.security

import com.d04nhtu.spring_eccomerce.models.MyUserDetails
import com.d04nhtu.spring_eccomerce.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserRepositoryUserDetailsService @Autowired constructor(val userRepo: UserRepository)
    : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepo.findByUsername(username)
        if (user != null) {
            return MyUserDetails(user.username, user.password)
        }
        throw  UsernameNotFoundException("User '$username' not found")
    }

}