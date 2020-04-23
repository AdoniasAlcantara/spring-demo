package dev.proj.springdemo.request

import javax.validation.constraints.Email
import javax.validation.constraints.Size

data class CustomerRequest(
        @field:Size(max = 60)
        val name: String,

        @field:Email
        @field:Size(max = 255)
        val email: String,

        @field:Size(min = 8, max = 20)
        val phone: String
)