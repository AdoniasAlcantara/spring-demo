package dev.proj.springdemo.response

import java.time.OffsetDateTime

class CommentResponse(
        val id: Long,
        val description: String,
        val sendAt: OffsetDateTime
)