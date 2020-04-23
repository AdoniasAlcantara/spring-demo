package dev.proj.springdemo.error

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.NOT_FOUND

abstract class ApiException(val status: HttpStatus, override val message: String = "") : RuntimeException(message)

class ResourceNotFoundException(message: String = "Resource not found")
    : ApiException(NOT_FOUND, message)

class DuplicateResourceException(val field: String, val duplicateValue: Any, message: String = "Duplicated value")
    : ApiException(CONFLICT, message)