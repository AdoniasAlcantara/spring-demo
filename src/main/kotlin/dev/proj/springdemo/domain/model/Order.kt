package dev.proj.springdemo.domain.model

import dev.proj.springdemo.error.InvalidOperationException
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
    fun makeCompleted() = makeWithStatus(Status.COMPLETED)

    fun makeCanceled() = makeWithStatus(Status.CANCELED)

    private fun makeWithStatus(status: Status): Order {
        if (this.status != Status.PENDING) {
            throw InvalidOperationException("Not allowed to change order status to $status")
        }

        return copy(status = status, end = OffsetDateTime.now())
    }

    enum class Status {
        PENDING, COMPLETED, CANCELED
    }
}