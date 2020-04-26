package dev.proj.springdemo.controller

import dev.proj.springdemo.request.CommentRequest
import dev.proj.springdemo.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/orders/{orderId}/comments")
class CommentController(private val service: OrderService) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun addComment(
            @PathVariable orderId: Long,
            @Valid @RequestBody request: CommentRequest
    ) = service.addComment(orderId, request)
}