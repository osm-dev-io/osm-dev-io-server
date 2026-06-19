package osm.dev.io.server.domain.user.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import osm.dev.io.server.domain.user.User
import osm.dev.io.server.domain.user.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun signup(email: String, password: String) {
        if(userRepository.existsByEmail(email)) {
            throw IllegalArgumentException("이미 사용 중인 이메일입니다: $email")
        }

        val encodedPassword = passwordEncoder.encode(password) ?: error("비밀번호 인코딩에 실패했습니다")
        val newUser = User(email, encodedPassword)
        userRepository.save(newUser)
    }
}