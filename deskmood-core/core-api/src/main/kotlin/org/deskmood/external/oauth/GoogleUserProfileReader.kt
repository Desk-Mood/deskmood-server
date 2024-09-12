package org.deskmood.external.oauth

import org.deskmood.domain.user.Gender
import org.deskmood.google.auth.GoogleAuthClient
import org.deskmood.google.user.GoogleUserClient
import org.springframework.stereotype.Component

@Component
class GoogleUserProfileReader(
    private val googleAuthClient: GoogleAuthClient,
    private val googleUserClient: GoogleUserClient
) : OauthUserProfileReader {

    override fun read(
        code: String,
        redirectUri: String
    ): OauthUserProfile {
        val authToken = googleAuthClient.provideAuthToken(code, redirectUri)
        val profile = googleUserClient.readUserProfile(authToken.accessToken)

        return OauthUserProfile(
            profile.email,
            profile.name,
            profile.birth,
            Gender.resolve(profile.gender)
        )
    }
}
