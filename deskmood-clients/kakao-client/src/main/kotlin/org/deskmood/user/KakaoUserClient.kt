package org.deskmood.user

import org.springframework.stereotype.Component

@Component
class KakaoUserClient(
    private val kakaoUserApi: KakaoUserApi
) {

    fun readUserProfile(accessToken: String): KakaoUserProfile {
        val response = kakaoUserApi.readUserProfile(
            bearerToken = "Bearer $accessToken",
            secureResource = true
        )

        return response.toKakaoUserProfile()
    }
}
