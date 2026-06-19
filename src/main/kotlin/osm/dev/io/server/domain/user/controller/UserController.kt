package osm.dev.io.server.domain.user.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import osm.dev.io.server.common.ApiResponse
import osm.dev.io.server.domain.user.controller.dto.SignupRequest
import osm.dev.io.server.domain.user.service.UserService

@RestController
@RequestMapping("users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signup(
        @RequestBody request: SignupRequest
    ): ApiResponse<Nothing> {
        userService.signup(request.email, request.password)
        return ApiResponse.success()
    }
}