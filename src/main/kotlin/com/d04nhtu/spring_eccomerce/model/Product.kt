package com.d04nhtu.spring_eccomerce.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "products")
class Product(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var name: String = "",
    var brand: String = "",
    var description: String = "",
    var saleOff: Boolean = false,
    var price: Double = 0.0,
    var pictureUrl: String = ""
)

