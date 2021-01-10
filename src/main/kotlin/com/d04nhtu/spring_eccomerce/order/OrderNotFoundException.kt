package com.d04nhtu.spring_eccomerce.order

class OrderNotFoundException(id: Long) : RuntimeException("Order $id not found!")