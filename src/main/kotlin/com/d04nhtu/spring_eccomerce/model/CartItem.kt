package com.d04nhtu.spring_eccomerce.model

import javax.persistence.*

@Entity(name = "cart_items")
class CartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long? = null,
    private var quantity: Int = 0,
    @ManyToOne
    var product: Product
)