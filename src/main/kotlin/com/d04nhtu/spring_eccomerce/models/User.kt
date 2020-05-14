package com.d04nhtu.spring_eccomerce.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity(name = "users")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
        @get: NotBlank val username: String = "",
        val password: String = "",
        val email: String = "",
        val address: String = "",
        val phoneNumber: String = ""
)