package dev.proj.springdemo.service

import dev.proj.springdemo.domain.model.Order
import dev.proj.springdemo.domain.repository.CommentRepository
import dev.proj.springdemo.domain.repository.CustomerRepository
import dev.proj.springdemo.domain.repository.OrderRepository
import dev.proj.springdemo.error.ResourceNotFoundException
import dev.proj.springdemo.request.CommentRequest
import dev.proj.springdemo.request.OrderRequest
import dev.proj.springdemo.request.model
import dev.proj.springdemo.response.CommentResponse
import dev.proj.springdemo.response.OrderResponse
import dev.proj.springdemo.response.response
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class OrderService(
        private val orderRepository: OrderRepository,
        private val customerRepository: CustomerRepository,
        private val commentRepository: CommentRepository
) {

    @Transactional(readOnly = true)
    fun getAll() = orderRepository.findAll().map(Order::response)

    fun getById(id: Long) = orderRepository.findByIdOrNull(id)
            ?.response()
            ?: throw ResourceNotFoundException("Could not find order with id $id")

    fun add(request: OrderRequest): OrderResponse {
        val customer = customerRepository.findByIdOrNull(request.customerId)
                ?: throw ResourceNotFoundException("Customer with id ${request.customerId} does not exist")

        val order = request.model(customer)
        return orderRepository.save(order).response()
    }

    fun addComment(orderId: Long, request: CommentRequest): CommentResponse {
        val order = orderRepository.findByIdOrNull(orderId)
                ?: throw ResourceNotFoundException("Order with id $orderId does not exist")

        val comment = request.model(order)
        return commentRepository.save(comment).response()
    }
}