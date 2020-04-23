package dev.proj.springdemo.util

import org.springframework.http.ResponseEntity

fun <T> ok(body: T) = ResponseEntity.ok(body)

fun <T> notFound() = ResponseEntity.notFound().build<T>()

fun <T> noContent() = ResponseEntity.noContent().build<T>()