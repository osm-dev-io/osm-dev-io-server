package osm.dev.io.server.domain.user.controller.dto

data class SignupRequest(
    val email: String,
    val password: String
)
