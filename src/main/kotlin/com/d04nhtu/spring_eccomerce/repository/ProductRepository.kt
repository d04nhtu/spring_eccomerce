package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.models.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
interface ProductRepository : CrudRepository<Product, Long> {
}