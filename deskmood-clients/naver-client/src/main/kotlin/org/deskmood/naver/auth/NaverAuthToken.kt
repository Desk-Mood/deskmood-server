package org.deskmood.naver.auth

data class NaverAuthToken(
    val accessToken: String,
    val refreshToken: String,
)
