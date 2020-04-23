package dev.proj.springdemo.request

import dev.proj.springdemo.domain.model.Customer

fun CustomerRequest.model(id: Long = 0) = Customer(id, name, email, phone)