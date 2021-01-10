package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.model.Cart
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : CrudRepository<Cart, Long>