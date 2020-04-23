package dev.proj.springdemo.controller

import dev.proj.springdemo.request.CustomerRequest
import dev.proj.springdemo.service.CustomerService
import dev.proj.springdemo.util.noContent
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
class CustomerController(private val service: CustomerService) {

    @GetMapping
    fun get(name: String?) = service.get(name)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(CREATED)
    fun add(@Valid @RequestBody request: CustomerRequest) = service.add(request)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: CustomerRequest) = service.update(id, request)

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Long): ResponseEntity<Nothing>  {
        service.remove(id)
        return noContent()
    }
}