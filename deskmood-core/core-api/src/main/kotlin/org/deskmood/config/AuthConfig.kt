package org.deskmood.config

import org.deskmood.auth.AuthProcessor
import org.deskmood.auth.ForbiddenHandler
import org.deskmood.auth.UnauthorizedHandler
import org.deskmood.support.UserIdResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AuthConfig(
    private val authProcessor: AuthProcessor,
    private val unauthorizedHandler: UnauthorizedHandler,
    private val forbiddenHandler: ForbiddenHandler
) : WebMvcConfigurer {

    override fun addArgumentResolvers(
        resolvers: MutableList<HandlerMethodArgumentResolver>
    ) {
        resolvers.add(UserIdResolver())
    }

    @Bean
    fun securityFilterChain(
        http: HttpSecurity
    ): SecurityFilterChain {
        http.httpBasic().disable().cors()
        http.csrf().disable()
        http.formLogin().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(authProcessor, BasicAuthenticationFilter::class.java)

        http.exceptionHandling()
            .accessDeniedHandler(forbiddenHandler)
            .authenticationEntryPoint(unauthorizedHandler)

        http
            .authorizeRequests()
            .anyRequest().permitAll()

        return http.build()
    }
}
