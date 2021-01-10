package com.d04nhtu.spring_eccomerce.model

import java.util.*
import javax.persistence.*

@Entity(name = "carts")
class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @OneToOne
    var user: DaoUser,
    @OneToMany(targetEntity = CartItem::class, cascade = [CascadeType.ALL])
    var cartItems: List<CartItem> = ArrayList()
)