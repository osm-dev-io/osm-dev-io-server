package osm.dev.io.server.domain.user.service

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import osm.dev.io.server.domain.user.User
import osm.dev.io.server.domain.user.repository.UserRepository

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun authenticate(email: String, password: String): User {
        val user = userRepository.findByEmail(email)
            ?: throw BadCredentialsException("이메일 또는 비밀번호가 올바르지 않습니다")

        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException("이메일 또는 비밀번호가 올바르지 않습니다")
        }

        return user
    }
}