package dev.proj.springdemo.domain.model

import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.persistence.*
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY

@Entity
data class Order(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        val id: Long,
        val description: String,
        val price: BigDecimal,

        @Enumerated(STRING)
        val status: Status,
        val start: OffsetDateTime,
        val end: OffsetDateTime?,

        @ManyToOne
        @JoinColumn(name = "customerId")
        val customer: Customer,

        @OneToMany(mappedBy = "order")
        val comments: List<Comment>
) {
    enum class Status {
        PENDING, COMPLETED, CANCELED
    }
}