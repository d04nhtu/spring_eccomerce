package com.d04nhtu.spring_eccomerce.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ResourceController {

    @RequestMapping("/hellouser")
    fun getUser(): String {
        return "Hello User"
    }

    @RequestMapping("/helloadmin")
    fun getAdmin(): String {
        return "Hello Admin"
    }

}