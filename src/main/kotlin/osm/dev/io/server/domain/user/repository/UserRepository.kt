package osm.dev.io.server.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import osm.dev.io.server.domain.user.User

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean
}