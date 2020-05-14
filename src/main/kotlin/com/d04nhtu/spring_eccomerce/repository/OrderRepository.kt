package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.models.Order
import com.d04nhtu.spring_eccomerce.models.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository


interface OrderRepository : CrudRepository<Order, Long> {
    fun findByUserOrderByPlacedAtDesc(user: User)

//    fun findByUserOrderByPlacedAtDesc(
//            user: User, pageable: Pageable
//    )
}