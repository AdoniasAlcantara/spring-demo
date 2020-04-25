package dev.proj.springdemo.response

import dev.proj.springdemo.domain.model.Order

fun Order.response() = OrderResponse(
        id,
        description,
        price,
        status.name,
        start,
        end,
        CustomerResponse(customer.id, customer.name)
)