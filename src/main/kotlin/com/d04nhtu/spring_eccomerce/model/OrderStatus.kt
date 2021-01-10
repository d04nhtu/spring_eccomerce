package com.d04nhtu.spring_eccomerce.model

enum class OrderStatus {
    BEING_CREATED, PAID_FOR, FULFILLED, CANCELLED;

    companion object {
        /**
         * Verify the transition between [OrderStatus] is valid. NOTE: This is where any/all rules for state transitions
         * should be kept and enforced.
         */
        fun valid(currentStatus: OrderStatus, newStatus: OrderStatus): Boolean {
            return when (currentStatus) {
                BEING_CREATED -> newStatus == PAID_FOR || newStatus == CANCELLED
                PAID_FOR -> newStatus == FULFILLED
                FULFILLED -> false
                CANCELLED -> false
                else -> throw RuntimeException("Unrecognized situation.")
            }
        }
    }
}