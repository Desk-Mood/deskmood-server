package org.deskmood.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverAuthResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("refresh_token")
    val refreshToken: String,
) {

    fun toNaverAuthToken(): NaverAuthToken {
        return NaverAuthToken(accessToken, refreshToken)
    }
}
