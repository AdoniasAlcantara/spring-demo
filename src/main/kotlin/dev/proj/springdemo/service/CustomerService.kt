package dev.proj.springdemo.service

import dev.proj.springdemo.domain.model.Customer
import dev.proj.springdemo.domain.repository.CustomerRepository
import dev.proj.springdemo.error.DuplicateResourceException
import dev.proj.springdemo.error.ResourceNotFoundException
import dev.proj.springdemo.request.CustomerRequest
import dev.proj.springdemo.request.model
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerService(private val repository: CustomerRepository) {

    fun get(name: String?) = name
            ?.let(repository::findByNameContaining)
            ?: repository.findAll()

    fun getById(id: Long) = repository.findByIdOrNull(id)
            ?: throw ResourceNotFoundException("Could not find customer with id $id")

    fun add(request: CustomerRequest): Customer {
        if (repository.existsByEmail(request.email)) {
            throw DuplicateResourceException("email", request.email, "Email already exists")
        }

        return repository.save(request.model())
    }

    fun update(id: Long, request: CustomerRequest): Customer {
        if (!repository.existsById(id)) {
            throw ResourceNotFoundException("Customer with id $id does not exist")
        }

        return repository.save(request.model(id))
    }


    fun remove(id: Long) {
        if (!repository.existsById(id)) {
            throw ResourceNotFoundException("Customer with id $id does not exist")
        }

        repository.deleteById(id)
    }
}