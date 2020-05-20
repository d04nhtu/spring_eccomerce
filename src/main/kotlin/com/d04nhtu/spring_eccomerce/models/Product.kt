package com.d04nhtu.spring_eccomerce.models

import com.sun.istack.NotNull
import org.springframework.data.rest.core.annotation.RestResource
import javax.persistence.*

@Entity(name = "products")
class Product(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        var name: String = "",
        var brand: String = "",
        var description: String = "",
        var saleOff: Boolean = false,
        var price: Double = 0.0,
        var pictureUrl: String = "")

