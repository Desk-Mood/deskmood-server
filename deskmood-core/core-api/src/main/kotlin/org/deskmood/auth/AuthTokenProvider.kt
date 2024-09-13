package org.deskmood.auth

import io.wwan13.wintersecurity.jwt.TokenGenerator
import org.springframework.stereotype.Component

@Component
class AuthTokenProvider(
    private val tokenGenerator: TokenGenerator
) {

    fun provide(tokenPayload: TokenPayload): AuthToken {
        return AuthToken(
            accessToken = tokenGenerator.accessToken(tokenPayload),
            refreshToken = tokenGenerator.refreshToken(tokenPayload)
        )
    }
}
