package dev.proj.springdemo.domain.model

import java.time.OffsetDateTime
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
data class Comment(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        val id: Long,
        val description: String,
        val sendAt: OffsetDateTime,

        @ManyToOne
        @JoinColumn(name = "orderId")
        val order: Order
)