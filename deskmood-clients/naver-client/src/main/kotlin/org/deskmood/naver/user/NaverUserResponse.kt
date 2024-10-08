package org.deskmood.naver.user

import com.fasterxml.jackson.annotation.JsonProperty
import org.deskmood.datetime.DateTimeUtil

data class NaverUserResponse(
    val response: NaverUserInnerResponse
) {

    fun toNaverUserProfile(): NaverUserProfile {
        return NaverUserProfile(
            response.id,
            response.name,
            response.profileImage,
            response.email,
            response.gender,
            DateTimeUtil.toLocalDate("${response.birthYear}-${response.birthDay}")
        )
    }
}

data class NaverUserInnerResponse(
    val name: String,
    val id: String,
    @JsonProperty("profile_image")
    val profileImage: String,
    val email: String,
    val gender: String,
    @JsonProperty("birthyear")
    val birthYear: String,
    @JsonProperty("birthday")
    val birthDay: String
)
