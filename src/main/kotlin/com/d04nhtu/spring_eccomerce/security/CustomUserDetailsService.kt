package com.d04nhtu.spring_eccomerce.security

import com.d04nhtu.spring_eccomerce.auth.CreateUserRequest
import com.d04nhtu.spring_eccomerce.model.DaoUser
import com.d04nhtu.spring_eccomerce.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userDao: UserRepository

    @Autowired
    lateinit var bcryptEncoder: PasswordEncoder

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val roles: List<SimpleGrantedAuthority>
        val user: DaoUser? = userDao.findByUsername(username)
        if (user != null) {
            roles = listOf(SimpleGrantedAuthority(user.role))
            return User(user.username, user.password, roles)
        }
        throw UsernameNotFoundException("User not found with the name $username")
    }

    fun existsByUsername(username: String): Boolean {
        return userDao.existsByUsername(username)
    }

    fun save(req: CreateUserRequest): DaoUser {
        val newUser = DaoUser()
        newUser.username = req.username
        newUser.password = bcryptEncoder.encode(req.password)
        newUser.role = req.role
        return userDao.save(newUser)
    }
}