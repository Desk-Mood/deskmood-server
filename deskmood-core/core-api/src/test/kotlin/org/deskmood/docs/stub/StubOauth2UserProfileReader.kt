package org.deskmood.docs.stub

import org.deskmood.domain.user.Gender
import org.deskmood.external.oauth.OauthUserProfile
import org.deskmood.external.oauth.OauthUserProfileReader
import java.time.LocalDate

class StubOauth2UserProfileReader : OauthUserProfileReader {
    override fun read(code: String, redirectUri: String): OauthUserProfile {
        return OauthUserProfile(
            "wwan13@naver.com", "kim", LocalDate.of(2001, 2, 22), Gender.MAN
        )
    }
}
