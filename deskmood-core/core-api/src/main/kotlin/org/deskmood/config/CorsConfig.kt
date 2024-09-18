package org.deskmood.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig(
    @Value("\${spring.profiles.active}")
    private val activeProfile: String
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        val allowOrigins = when (activeProfile) {
            "prod" -> PROD_ALLOW_ORIGINS
            "dev" -> DEV_ALLOW_ORIGINS
            "local" -> LOCAL_ALLOW_ORIGINS
            else -> throw IllegalStateException("Invalid Profile")
        }

        registry.apply {
            addMapping("/**").apply {
                allowedOrigins(*allowOrigins)
                allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                allowedHeaders("Authorization", "Content-Type", "Accept", "X-Requested-With", "Origin")
                allowCredentials(true)
                maxAge(3600)
            }
        }
    }

    companion object {
        val PROD_ALLOW_ORIGINS = arrayOf("https://deskmood.kr")

        val DEV_ALLOW_ORIGINS = arrayOf(
            "http://localhost:3000",
            "https://docs.deskmood.kr",
            "https://dev.deskmood.kr"
        )

        val LOCAL_ALLOW_ORIGINS = arrayOf("http://localhost:3000")
    }
}
