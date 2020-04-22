package dev.proj.springdemo.domain.repository

import dev.proj.springdemo.domain.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long>