package org.deskmood.kakao.auth

data class KakaoAuthToken(
    val accessToken: String,
    val refreshToken: String
)
