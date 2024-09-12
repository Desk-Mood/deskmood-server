package org.deskmood.google.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleAuthResponse(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("refresh_token")
    val refreshToken: String
) {

    fun toGoogleAuthToken(): GoogleAuthToken {
        return GoogleAuthToken(accessToken, refreshToken)
    }
}
