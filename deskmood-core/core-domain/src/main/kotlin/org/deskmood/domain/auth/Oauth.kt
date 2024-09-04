package org.deskmood.domain.auth

data class Oauth(
    val platform: OauthPlatform,
    val identifier: String
)
