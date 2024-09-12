package org.deskmood.external.oauth

import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.base.ValueEnum
import org.springframework.stereotype.Component

@Component
class OauthPlatformSelector(
    private val naverUserProfileReader: NaverUserProfileReader,
    private val googleUserProfileReader: GoogleUserProfileReader
) {

    fun select(value: String): OauthUserProfileReader {
        val platform = ValueEnum.resolve<OauthPlatform>(value)
        return when (platform) {
            OauthPlatform.NAVER -> naverUserProfileReader
            OauthPlatform.GOOGLE -> googleUserProfileReader
            OauthPlatform.LOCAL -> TODO()
        }
    }
}
