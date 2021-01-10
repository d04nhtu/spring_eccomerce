package com.d04nhtu.spring_eccomerce

import com.d04nhtu.spring_eccomerce.model.Product
import com.d04nhtu.spring_eccomerce.repository.ProductRepository
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringEccomerceApplication {
    @Bean
    fun dataLoader(repo: ProductRepository): CommandLineRunner {
        return CommandLineRunner {
            repo.save(
                Product(
                    null,
                    "Nokia 6300",
                    "Nokia",
                    "desc 6300",
                    false,
                    10.0,
                    "https://fdn2.gsmarena.com/vv/pics/nokia/nokia-6300-00.jpg"
                )
            )
            repo.save(
                Product(
                    null,
                    "Nokia e72",
                    "Nokia",
                    "desc e72",
                    true,
                    15.0,
                    "https://fdn.gsmarena.com/vv/reviewsimg/nokia-e72/phone/gsmarena_002.jpg"
                )
            )
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringEccomerceApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }

}
