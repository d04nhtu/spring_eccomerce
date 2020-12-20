package com.d04nhtu.spring_eccomerce.security

import com.d04nhtu.spring_eccomerce.models.DAOUser
import com.d04nhtu.spring_eccomerce.models.UserDTO
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
        val user: DAOUser? = userDao.findByUsername(username)
        if (user != null) {
            roles = listOf(SimpleGrantedAuthority(user.role))
            return User(user.username, user.password, roles)
        }
        throw UsernameNotFoundException("User not found with the name $username")
    }

    fun save(user: UserDTO): DAOUser {
        val newUser = DAOUser()
        newUser.username = user.username
        newUser.password = bcryptEncoder.encode(user.password)
        newUser.role = user.role
        return userDao.save(newUser)
    }
}