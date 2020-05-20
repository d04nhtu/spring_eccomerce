package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}