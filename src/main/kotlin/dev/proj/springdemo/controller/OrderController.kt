package dev.proj.springdemo.controller

import dev.proj.springdemo.request.OrderRequest
import dev.proj.springdemo.service.OrderService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
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
    @ResponseStatus(CREATED)
    fun submit(@Valid @RequestBody request: OrderRequest) = service.submit(request)

    @PutMapping("/{id}/complete")
    @ResponseStatus(NO_CONTENT)
    fun complete(@PathVariable id: Long) = service.complete(id)

    @PutMapping("/{id}/cancel")
    @ResponseStatus(NO_CONTENT)
    fun cancel(@PathVariable id: Long) = service.cancel(id)
}