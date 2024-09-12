package org.deskmood.google.auth

import org.deskmood.google.config.GoogleProperties
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
