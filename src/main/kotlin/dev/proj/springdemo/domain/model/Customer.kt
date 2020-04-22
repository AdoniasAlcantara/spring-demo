package dev.proj.springdemo.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity
data class Customer(
        @Id
        @GeneratedValue(strategy = IDENTITY)
        val id: Long,
        val name: String,
        val email: String,
        val phone: String
)