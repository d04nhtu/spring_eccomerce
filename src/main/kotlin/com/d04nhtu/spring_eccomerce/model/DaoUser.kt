package com.d04nhtu.spring_eccomerce.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity(name = "users")
class DaoUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    @get: NotBlank var username: String = "",
    var password: String = "",
    var role: String = "",
    var email: String = "",
    var address: String = "",
    var phoneNumber: String = ""
)
