package org.deskmood.auth

import com.fasterxml.jackson.annotation.JsonProperty
import org.deskmood.error.DeskmoodException
import org.deskmood.error.NaverClientError

data class NaverAuthResponse(
    @JsonProperty("access_token")
    val accessToken: String?,
    @JsonProperty("refresh_token")
    val refreshToken: String?,
    val error: String?,
    @JsonProperty("error_description")
    val errorDescription: String?
) {

    val hasError: Boolean
        get() = !error.isNullOrBlank()

    fun toNaverAuthToken(): NaverAuthToken {
        return NaverAuthToken(
            accessToken ?: throw DeskmoodException(NaverClientError),
            refreshToken ?: throw DeskmoodException(NaverClientError)
        )
    }
}
