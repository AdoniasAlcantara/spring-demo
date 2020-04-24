package dev.proj.springdemo.controller

import dev.proj.springdemo.request.OrderRequest
import dev.proj.springdemo.service.OrderService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/orders")
class OrderController(private val service: OrderService) {

    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    fun submit(@Valid @RequestBody request: OrderRequest) = service.add(request)
}