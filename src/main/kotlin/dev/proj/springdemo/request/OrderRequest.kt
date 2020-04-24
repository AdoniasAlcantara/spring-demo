package dev.proj.springdemo.request

import java.math.BigDecimal
import javax.validation.constraints.NotBlank

class OrderRequest(
        val customerId: Long,

        @NotBlank
        val description: String,
        val price: BigDecimal
)