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
            grantType = "authorization_code",
            clientId = kakaoProperties.appKey,
            redirectUri = redirectUri,
            code = code
        )
        return response.toKakaoAuthToken()
    }

    fun refreshToken(refreshToken: String): KakaoAuthToken {
        val response = kakaoAuthApi.refreshToken(
            grantType = "refresh_token",
            clientId = kakaoProperties.appKey,
            refreshToken = refreshToken
        )
        return response.toKakaoAuthToken()
    }
}
