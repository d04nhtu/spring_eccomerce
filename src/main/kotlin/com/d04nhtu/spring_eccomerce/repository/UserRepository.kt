package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.model.DaoUser
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<DaoUser, Long> {
    fun findByUsername(username: String): DaoUser?

    fun existsByUsername(username: String): Boolean
}