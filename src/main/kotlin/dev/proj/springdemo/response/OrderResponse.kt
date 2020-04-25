package dev.proj.springdemo.response

import java.math.BigDecimal
import java.time.OffsetDateTime

data class OrderResponse(
        val id: Long,
        val description: String,
        val price: BigDecimal,
        val status: String,
        val start: OffsetDateTime,
        val end: OffsetDateTime?,
        val customer: CustomerResponse
)