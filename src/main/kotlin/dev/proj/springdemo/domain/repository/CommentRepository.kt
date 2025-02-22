package dev.proj.springdemo.domain.repository

import dev.proj.springdemo.domain.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long>