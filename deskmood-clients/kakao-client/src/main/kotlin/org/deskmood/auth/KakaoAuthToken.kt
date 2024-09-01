package org.deskmood.auth

data class KakaoAuthToken(
    val accessToken: String,
    val refreshToken: String
)
