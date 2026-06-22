package osm.dev.io.server.common.exception

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import osm.dev.io.server.common.ApiError
import osm.dev.io.server.common.ApiResponse
import tools.jackson.databind.ObjectMapper

@Component
class RestAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        val body = ApiResponse.error(
            ApiError(code = "ACCESS_DENIED", message = "접근 권한이 없습니다")
        )
        response.writer.write(objectMapper.writeValueAsString(body))
    }
}