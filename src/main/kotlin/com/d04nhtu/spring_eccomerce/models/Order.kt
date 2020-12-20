package com.d04nhtu.spring_eccomerce.models

import org.hibernate.validator.constraints.CreditCardNumber
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

//const val serialVersionUID: Long = 1L

@Entity(name = "orders")
class Order(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var placedAt: Date? = null,

    @ManyToOne
    var user: DAOUser,

    @NotBlank(message = "Zip code is required")
    var deliveryAddress: String,

    @CreditCardNumber(message = "Not a valid credit card number")
    var ccNumber: String,

    @Pattern(
        regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$",
        message = "Must be formatted MM/YY"
    )
    var ccExpiration: String,

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    var ccCVV: String,

    @ManyToMany(targetEntity = Product::class)
    var products: List<Product> = ArrayList()
)
//    : Serializable
{

    @PrePersist
    fun placedAt() {
        placedAt = Date()
    }
}

