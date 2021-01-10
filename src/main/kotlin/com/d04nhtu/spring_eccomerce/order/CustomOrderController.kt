package com.d04nhtu.spring_eccomerce.order

import com.d04nhtu.spring_eccomerce.model.Order
import com.d04nhtu.spring_eccomerce.model.OrderStatus
import com.d04nhtu.spring_eccomerce.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.BasePathAwareController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@BasePathAwareController
class CustomOrderController {

    @Autowired
    lateinit var repository: OrderRepository

    @PostMapping("/orders/{id}/pay")
    fun pay(@PathVariable id: Long): ResponseEntity<*> {
        val order: Order = repository.findById(id).orElseThrow<RuntimeException> {
            OrderNotFoundException(id)
        }
        if (OrderStatus.valid(order.orderStatus, OrderStatus.PAID_FOR)) {
            order.orderStatus = OrderStatus.PAID_FOR
            return ResponseEntity.ok<Any>(repository.save(order))
        }
        return ResponseEntity.badRequest()
            .body<Any>(
                "Transitioning from " + order.orderStatus
                    .toString() + " to " + OrderStatus.PAID_FOR.toString() + " is not valid."
            )
    }

    @PostMapping("/orders/{id}/cancel")
    fun cancel(@PathVariable id: Long): ResponseEntity<*> {
        val order: Order = repository.findById(id).orElseThrow<RuntimeException> {
            OrderNotFoundException(id)
        }
        if (OrderStatus.valid(order.orderStatus, OrderStatus.CANCELLED)) {
            order.orderStatus = OrderStatus.CANCELLED
            return ResponseEntity.ok<Any>(repository.save(order))
        }
        return ResponseEntity.badRequest()
            .body<Any>(
                "Transitioning from " + order.orderStatus
                    .toString() + " to " + OrderStatus.CANCELLED.toString() + " is not valid."
            )
    }

    @PostMapping("/orders/{id}/fulfill")
    fun fulfill(@PathVariable id: Long): ResponseEntity<*> {
        val order: Order = repository.findById(id).orElseThrow<RuntimeException> {
            OrderNotFoundException(id)
        }
        if (OrderStatus.valid(order.orderStatus, OrderStatus.FULFILLED)) {
            order.orderStatus = OrderStatus.FULFILLED
            return ResponseEntity.ok<Any>(repository.save(order))
        }
        return ResponseEntity.badRequest()
            .body<Any>(
                "Transitioning from " + order.orderStatus
                    .toString() + " to " + OrderStatus.FULFILLED.toString() + " is not valid."
            )
    }
}