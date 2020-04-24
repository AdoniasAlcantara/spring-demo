package dev.proj.springdemo.service

import dev.proj.springdemo.domain.model.Order
import dev.proj.springdemo.domain.repository.CustomerRepository
import dev.proj.springdemo.domain.repository.OrderRepository
import dev.proj.springdemo.error.ResourceNotFoundException
import dev.proj.springdemo.request.OrderRequest
import dev.proj.springdemo.request.model
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class OrderService(
        private val orderRepository: OrderRepository,
        private val customerRepository: CustomerRepository
) {

    fun getAll(): List<Order> = orderRepository.findAll()

    fun getById(id: Long): Order = orderRepository.findByIdOrNull(id)
            ?: throw ResourceNotFoundException("Could not find order with id $id")

    fun add(request: OrderRequest): Order {
        val customer = customerRepository.findByIdOrNull(request.customerId)
                ?: throw ResourceNotFoundException("Customer with id ${request.customerId} does not exist")

        val order = request.model(customer)
        return orderRepository.save(order)
    }
}