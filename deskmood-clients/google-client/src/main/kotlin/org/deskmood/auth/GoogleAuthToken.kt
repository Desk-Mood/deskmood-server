package org.deskmood.auth

data class GoogleAuthToken(
    val accessToken: String,
    val refreshToken: String
)
