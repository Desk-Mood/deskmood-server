package org.deskmood.user

data class NaverUserResponse(
    val response: NaverUserInnerResponse
) {

    fun toNaverUserProfile(): NaverUserProfile {
        return NaverUserProfile(response.id, response.name)
    }
}

data class NaverUserInnerResponse(
    val name: String,
    val id: String
)
