package org.deskmood.auth

data class AuthToken(
    val accessToken: String,
    val refreshToken: String
)
