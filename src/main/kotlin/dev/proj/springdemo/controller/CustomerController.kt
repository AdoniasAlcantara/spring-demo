package dev.proj.springdemo.controller

import dev.proj.springdemo.domain.model.Customer
import dev.proj.springdemo.domain.repository.CustomerRepository
import dev.proj.springdemo.request.CustomerRequest
import dev.proj.springdemo.request.map
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
class CustomerController(private val repository: CustomerRepository) {

    @GetMapping
    fun get(name: String?) = name
            ?.let(repository::findByNameContaining)
            ?: repository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = repository.findByIdOrNull(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PostMapping
    @ResponseStatus(CREATED)
    fun add(@Valid @RequestBody customer: CustomerRequest) = repository.save(customer.map())

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: CustomerRequest): ResponseEntity<Customer> {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }

        val customer = with(request.map(id)) {
            repository.save(this)
        }

        return ResponseEntity.ok(customer)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Nothing> {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }

        repository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}