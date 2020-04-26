package dev.proj.springdemo.error

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

abstract class ApiException(val status: HttpStatus, override val message: String = "") : RuntimeException(message)

class ResourceNotFoundException(message: String = "Resource not found")
    : ApiException(NOT_FOUND, message)

class DuplicateResourceException(val field: String, val duplicateValue: Any, message: String = "Duplicated value")
    : ApiException(CONFLICT, message)

class InvalidOperationException(message: String = "Invalid operation")
    : ApiException(BAD_REQUEST, message)