package org.deskmood.controller.auth.dto

data class LoginRequest(
    val oauth2Code: String,
    val redirectUri: String
)
