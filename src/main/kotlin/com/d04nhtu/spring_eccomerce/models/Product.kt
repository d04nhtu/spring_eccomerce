package com.d04nhtu.spring_eccomerce.models

import com.sun.istack.NotNull
import org.springframework.data.rest.core.annotation.RestResource
import javax.persistence.*

@Entity(name = "products")
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        val name: String = "",
        val brand: String = "",
        val description: String = "",
        val saleOff: Boolean = false,
        val price: Double,
        val pictureUrl: String)

