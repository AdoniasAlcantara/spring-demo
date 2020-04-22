package dev.proj.springdemo.controller

import dev.proj.springdemo.domain.model.Customer
import dev.proj.springdemo.domain.repository.CustomerRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(private val repository: CustomerRepository) {

    @GetMapping("/customers")
    fun getCustomers(): List<Customer> = repository.findAll()
}