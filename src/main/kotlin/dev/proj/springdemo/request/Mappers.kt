package dev.proj.springdemo.request

import dev.proj.springdemo.domain.model.Customer

fun CustomerRequest.map(id: Long = 0L) = Customer(id, name, email, phone)