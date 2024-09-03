package org.deskmood.auth

data class NaverAuthToken(
    val accessToken: String,
    val refreshToken: String,
)
