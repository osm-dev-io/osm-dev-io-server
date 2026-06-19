package osm.dev.io.server.domain.user.controller.dto

data class LoginRequest(
    val email: String,
    val password: String
)
