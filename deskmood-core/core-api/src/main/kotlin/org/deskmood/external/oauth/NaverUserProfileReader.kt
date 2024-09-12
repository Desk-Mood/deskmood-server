package org.deskmood.external.oauth

import org.deskmood.domain.user.Gender
import org.deskmood.naver.auth.NaverAuthClient
import org.deskmood.naver.user.NaverUserClient
import org.springframework.stereotype.Component

@Component
class NaverUserProfileReader(
    private val naverAuthClient: NaverAuthClient,
    private val naverUserClient: NaverUserClient
) : OauthUserProfileReader {

    override fun read(
        code: String,
        redirectUri: String
    ): OauthUserProfile {
        val authToken = naverAuthClient.provideAuthToken(code, redirectUri)
        val profile = naverUserClient.readUserProfile(authToken.accessToken)

        return OauthUserProfile(
            profile.email,
            profile.name,
            profile.birth,
            Gender.resolve(profile.gender)
        )
    }
}
