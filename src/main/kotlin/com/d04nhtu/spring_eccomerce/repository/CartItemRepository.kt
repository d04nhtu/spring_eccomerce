package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.model.CartItem
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : CrudRepository<CartItem, Long>