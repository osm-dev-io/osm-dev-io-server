package osm.dev.io.server.lib

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import osm.dev.io.server.lib.auth.AdminAuthInterceptor

@Configuration
class WebConfig(
    private val adminAuthInterceptor: AdminAuthInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(adminAuthInterceptor)
    }
}