package org.deskmood.user

data class GoogleUserResponse(
    val sub: String,
    val name: String,
    val picture: String
) {

    fun toGoogleUserProfile(): GoogleUserProfile {
        return GoogleUserProfile(sub, name, picture)
    }
}
