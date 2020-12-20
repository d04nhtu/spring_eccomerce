package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.models.DAOUser
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<DAOUser, Long> {
    fun findByUsername(username: String): DAOUser?
}