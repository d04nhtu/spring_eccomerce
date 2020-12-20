package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.models.Order
import com.d04nhtu.spring_eccomerce.models.DAOUser
import org.springframework.data.repository.CrudRepository


interface OrderRepository : CrudRepository<Order, Long> {
    fun findByUserOrderByPlacedAtDesc(user: DAOUser)

//    fun findByUserOrderByPlacedAtDesc(
//            user: User, pageable: Pageable
//    )
}