package org.deskmood.external.oauth

interface OauthUserProfileReader {
    fun read(code: String, redirectUri: String): OauthUserProfile
}
