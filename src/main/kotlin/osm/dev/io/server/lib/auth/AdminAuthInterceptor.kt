package osm.dev.io.server.lib.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import java.security.MessageDigest

@Component
class AdminAuthInterceptor(
    @Value("\${blog.admin.secret}") private val adminSecret: String
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if(handler !is HandlerMethod) return true

        val adminOnly = handler.getMethodAnnotation(AdminOnly::class.java)
            ?: handler.beanType.getAnnotation(AdminOnly::class.java)
        if(adminOnly == null) return true

        val provided = request.getHeader(ADMIN_SECRET_HEADER)
        if(provided == null || !isSecretValid(provided)) {
            throw AdminUnauthorizedException()
        }
        return true
    }

    private fun isSecretValid(provided: String): Boolean =
        MessageDigest.isEqual(
            provided.toByteArray(Charsets.UTF_8),
            adminSecret.toByteArray(Charsets.UTF_8)
        )

    companion object {
        const val ADMIN_SECRET_HEADER = "X-ADMIN-SECRET"
    }
}