package dev.proj.springdemo.error

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFound(ex: ResourceNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
                status = ex.status.value(),
                title = ex.message
        )

        return handleExceptionInternal(ex, errorResponse, HttpHeaders(), ex.status, request)
    }

    @ExceptionHandler(DuplicateResourceException::class)
    fun handleDuplicateResource(ex: DuplicateResourceException, request: WebRequest): ResponseEntity<Any> {
        val errorFields = listOf(
                ErrorField(
                        ex.field,
                        ex.message,
                        ex.duplicateValue
                )
        )

        val errorResponse = ValidationErrorResponse(
                status = ex.status.value(),
                title = "Could not proceed due to duplicate resource",
                errors = errorFields
        )

        return handleExceptionInternal(ex, errorResponse, HttpHeaders(), ex.status, request)
    }

    override fun handleMethodArgumentNotValid(
            ex: MethodArgumentNotValidException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest
    ): ResponseEntity<Any> {
        val errorFields = ex.bindingResult.allErrors.map { error ->
            error as FieldError
            ErrorField(
                    error.field,
                    error.defaultMessage ?: "unknown",
                    error.rejectedValue
            )
        }

        val errorResponse = ValidationErrorResponse(
                status = status.value(),
                title = "Validation error",
                errors = errorFields
        )

        return handleExceptionInternal(ex, errorResponse, headers, status, request)
    }
}
