package org.deskmood.user

import org.springframework.stereotype.Component

@Component
class GoogleUserClient(
    private val googleUserApi: GoogleUserApi
) {

    fun readUserProfile(accessToken: String): GoogleUserProfile {
        val response = googleUserApi.readUserProfile(
            bearerToken = "Bearer $accessToken",
        )

        return response.toGoogleUserProfile()
    }
}
