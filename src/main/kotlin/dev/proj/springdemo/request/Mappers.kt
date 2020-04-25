package dev.proj.springdemo.request

import dev.proj.springdemo.domain.model.Customer
import dev.proj.springdemo.domain.model.Order
import dev.proj.springdemo.domain.model.Order.Status.PENDING
import java.time.OffsetDateTime

fun CustomerRequest.model(id: Long = 0) = Customer(id, name, email, phone)

fun OrderRequest.model(customer: Customer) = Order(
        0,
        description,
        price,
        PENDING,
        OffsetDateTime.now(),
        null,
        customer
)