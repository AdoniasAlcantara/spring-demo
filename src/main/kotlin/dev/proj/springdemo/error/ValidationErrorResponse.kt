package dev.proj.springdemo.error

import java.time.LocalDateTime

class ValidationErrorResponse(
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val status: Int,
        val title: String,
        val errors: List<ErrorField>
)