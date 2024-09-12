package org.deskmood.google.auth

data class GoogleAuthToken(
    val accessToken: String,
    val refreshToken: String
)
