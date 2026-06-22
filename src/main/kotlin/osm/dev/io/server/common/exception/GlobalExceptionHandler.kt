package osm.dev.io.server.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import osm.dev.io.server.common.ApiError
import osm.dev.io.server.common.ApiResponse
import osm.dev.io.server.common.FieldErrorDetail

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentials(e: BadCredentialsException): ResponseEntity<ApiResponse<Nothing>> {
        val body = ApiResponse.error(
            ApiError(code = "INVALID_CREDENTIALS", message = e.message ?: "인증 정보가 올바르지 않습니다")
        )
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(e: IllegalArgumentException): ResponseEntity<ApiResponse<Nothing>> {
        val body = ApiResponse.error(
            ApiError(code = "BAD_REQUEST", message = e.message ?: "잘못된 요청입니다")
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing>> {
        val details = e.bindingResult.fieldErrors.map {
            FieldErrorDetail(field = it.field, reason = it.defaultMessage ?: "유효하지 않은 값입니다")
        }
        val body = ApiResponse.error(
            ApiError(code = "VALIDATION_FAILED", message = "입력값이 올바르지 않습니다", detail = details)
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Nothing>> {
        val body = ApiResponse.error(
            ApiError(code = "INTERNAL_ERROR", message = "서버 오류가 발생했습니다")
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body)
    }
}