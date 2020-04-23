package dev.proj.springdemo.error

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {

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

        val errorResponse = ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                "Validation error",
                errorFields
        )

        return super.handleExceptionInternal(ex, errorResponse, headers, status, request)
    }
}
