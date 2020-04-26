package dev.proj.springdemo.response

import dev.proj.springdemo.domain.model.Comment
import dev.proj.springdemo.domain.model.Order

fun Order.response() = OrderResponse(
        id,
        description,
        price,
        status.name,
        start,
        end,
        CustomerResponse(customer.id, customer.name),
        comments.map { it.response() }
)

fun Comment.response() = CommentResponse(id, description, sendAt)