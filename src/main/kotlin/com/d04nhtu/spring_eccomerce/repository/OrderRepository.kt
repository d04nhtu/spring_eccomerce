package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.model.DaoUser
import com.d04nhtu.spring_eccomerce.model.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Long> {
    fun findByUserOrderByPlacedAtDesc(daoUser: DaoUser)

//    fun findByUserOrderByPlacedAtDesc(
//            user: User, pageable: Pageable
//    )
}