package org.deskmood.auth

import org.deskmood.config.KakaoProperties
import org.springframework.stereotype.Component

@Component
class KakaoAuthClient(
    private val kakaoAuthApi: KakaoAuthApi,
    private val kakaoProperties: KakaoProperties
) {

    fun provideAuthToken(
        code: String,
        redirectUri: String
    ): KakaoAuthToken {
        val response = kakaoAuthApi.provideToken(
            clientId = kakaoProperties.appKey,
            redirectUri = redirectUri,
            code = code
        )
        return response.toKakaoAuthToken()
    }
}
