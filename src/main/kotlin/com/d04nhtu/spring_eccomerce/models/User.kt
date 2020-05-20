package com.d04nhtu.spring_eccomerce.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity(name = "users")
class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
        @get: NotBlank var username: String = "",
        var password: String = "",
        var email: String = "",
        var address: String = "",
        var phoneNumber: String = ""
)

//    : UserDetails {
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
//    }
//
//    override fun getPassword(): String {
//        return password
//    }
//
//    override fun getUsername(): String {
//        return username
//    }
//
//    override fun isEnabled(): Boolean {
//        return true
//    }
//
//    override fun isCredentialsNonExpired(): Boolean {
//        return true
//    }
//
//    override fun isAccountNonExpired(): Boolean {
//        return true
//    }
//
//    override fun isAccountNonLocked(): Boolean {
//        return true
//    }
//
//}