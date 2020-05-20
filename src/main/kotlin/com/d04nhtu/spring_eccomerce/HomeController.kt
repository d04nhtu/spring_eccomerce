package com.d04nhtu.spring_eccomerce

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Controller
//@EnableWebMvc
//@EnableAutoConfiguration
class HtmlController {

    @GetMapping("/")
    fun blog(): String {

        return "home.html"
    }
}