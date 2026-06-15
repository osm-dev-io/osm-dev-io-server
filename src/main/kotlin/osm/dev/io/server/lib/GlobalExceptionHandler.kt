package osm.dev.io.server.lib

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import osm.dev.io.server.lib.auth.AdminUnauthorizedException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AdminUnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleAdminUnauthorized(e: AdminUnauthorizedException): ApiResponse<Nothing> =
        ApiResponse.error(ApiError(code = "UNAUTHORIZED", message = e.message ?: "권한이 없습니다."))
}