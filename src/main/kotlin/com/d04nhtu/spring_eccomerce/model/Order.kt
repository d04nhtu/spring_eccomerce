package com.d04nhtu.spring_eccomerce.model

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
    var id: Long = -1,

    var orderStatus: OrderStatus = OrderStatus.BEING_CREATED,

    var placedAt: Date = Date(),

    @OneToOne
    var user: DaoUser,

    @OneToOne
    var cart: Cart,

    @get: NotBlank(message = "Zip code is required")
    var deliveryAddress: String,

    @get: CreditCardNumber(message = "Not a valid credit card number")
    var ccNumber: String,

    @get: Pattern(
        regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$",
        message = "Must be formatted MM/YY"
    )
    var ccExpiration: String,

    @get: Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    var ccCVV: String
)
//    : Serializable

