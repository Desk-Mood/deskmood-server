package org.deskmood.controller.auth.dto

import org.deskmood.auth.AuthToken

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
) {

    companion object {
        fun from(authToken: AuthToken): LoginResponse {
            return LoginResponse(authToken.accessToken, authToken.refreshToken)
        }
    }
}
