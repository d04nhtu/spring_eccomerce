package com.d04nhtu.spring_eccomerce.models

import org.hibernate.validator.constraints.CreditCardNumber
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import kotlin.collections.ArrayList

//const val serialVersionUID: Long = 1L

@Entity(name = "orders")
data class Order(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        var placedAt: Date? = null,

        @ManyToOne
        val user: User,

        @NotBlank(message = "Zip code is required")
        val deliveryAddress: String,

        @CreditCardNumber(message = "Not a valid credit card number")
        val ccNumber: String,

        @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
                message = "Must be formatted MM/YY")
        val ccExpiration: String,

        @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
        val ccCVV: String,

        @ManyToMany(targetEntity = Product::class)
        val products: List<Product> = ArrayList())
//    : Serializable
{

    @PrePersist
    fun placedAt() {
        placedAt = Date()
    }
}

