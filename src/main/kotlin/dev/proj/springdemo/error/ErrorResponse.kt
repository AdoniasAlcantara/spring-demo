package dev.proj.springdemo.error

import java.time.LocalDateTime

data class ErrorResponse(
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val status: Int,
        val title: String
)