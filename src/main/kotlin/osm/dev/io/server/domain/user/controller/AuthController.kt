package osm.dev.io.server.domain.user.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.SecurityContextRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import osm.dev.io.server.common.ApiResponse
import osm.dev.io.server.domain.user.controller.dto.LoginRequest
import osm.dev.io.server.domain.user.service.AuthService

@RestController
class AuthController(
    private val authService: AuthService,
    private val securityContextRepository: SecurityContextRepository
) {
    @PostMapping("login")
    fun login(
        @RequestBody request: LoginRequest,
        httpRequest: HttpServletRequest,
        httpResponse: HttpServletResponse
    ): ApiResponse<Nothing> {
        val user = authService.authenticate(request.email, request.password)

        val authorities = listOf(SimpleGrantedAuthority("ROLE_${user.role.name}"))
        val authentication = UsernamePasswordAuthenticationToken(user.email, null, authorities)

        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = authentication
        SecurityContextHolder.setContext(context)

        securityContextRepository.saveContext(context, httpRequest, httpResponse)

        return ApiResponse.success()
    }

    @PostMapping("logout")
    fun logout(httpRequest: HttpServletRequest): ApiResponse<Nothing> {
        httpRequest.getSession(false)?.invalidate()

        SecurityContextHolder.clearContext()

        return ApiResponse.success()
    }
}