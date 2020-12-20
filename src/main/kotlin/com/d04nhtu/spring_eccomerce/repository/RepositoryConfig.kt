package com.d04nhtu.spring_eccomerce.repository

import com.d04nhtu.spring_eccomerce.models.Order
import com.d04nhtu.spring_eccomerce.models.Product
import com.d04nhtu.spring_eccomerce.models.DAOUser
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer

@Configuration
class RepositoryConfig : RepositoryRestConfigurer {
    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?) {
        super.configureRepositoryRestConfiguration(config)
        config?.exposeIdsFor(Product::class.java, Order::class.java, DAOUser::class.java)
    }
}