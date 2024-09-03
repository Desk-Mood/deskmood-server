package org.deskmood.user

import org.springframework.stereotype.Component

@Component
class NaverUserClient(
    private val naverUserApi: NaverUserApi
) {

    fun readUserProfile(accessToken: String): NaverUserProfile {
        val response = naverUserApi.readUserProfile("Bearer $accessToken")
        return response.toNaverUserProfile()
    }
}
