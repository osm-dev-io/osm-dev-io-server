package osm.dev.io.server.domain.user

import java.io.Serializable

data class LoginUser(
    val userId: Long,
    val email: String,
    val role: Role
) : Serializable