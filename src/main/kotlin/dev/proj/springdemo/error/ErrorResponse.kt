package dev.proj.springdemo.error

import java.time.LocalDateTime

data class ErrorResponse(
        val timestamp: LocalDateTime,
        val status: Int,
        val title: String,
        val errors: List<ErrorField>
)