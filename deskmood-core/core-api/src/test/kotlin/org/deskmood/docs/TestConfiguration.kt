package org.deskmood.docs

import com.fasterxml.jackson.databind.ObjectMapper
import io.wwan13.wintersecurity.jwt.TokenDecoder
import org.deskmood.auth.ForbiddenHandler
import org.deskmood.auth.UnauthorizedHandler
import org.deskmood.docs.stub.StubTokenDecoder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class TestConfiguration {

    @Bean
    fun tokenDecoder(): TokenDecoder {
        return StubTokenDecoder()
    }

    @Bean
    fun unauthorizedHandler(
        objectMapper: ObjectMapper
    ): UnauthorizedHandler {
        return UnauthorizedHandler(objectMapper)
    }

    @Bean
    fun forbiddenHandler(
        objectMapper: ObjectMapper
    ): ForbiddenHandler {
        return ForbiddenHandler(objectMapper)
    }
}
