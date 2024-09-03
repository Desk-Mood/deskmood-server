package org.deskmood.auth

import org.deskmood.config.GoogleProperties
import org.springframework.stereotype.Component

@Component
class GoogleAuthClient(
    private val googleAuthApi: GoogleAuthApi,
    private val googleProperties: GoogleProperties
) {

    fun provideAuthToken(
        code: String,
        state: String
    ): GoogleAuthToken {
        val response = googleAuthApi.provideToken(
            clientId = googleProperties.clientId,
            clientSecret = googleProperties.clientSecret,
            state = state,
            code = code
        )
        return response.toGoogleAuthToken()
    }
}
