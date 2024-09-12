package org.deskmood.controller.user.dto

import org.deskmood.datetime.DateTimeUtil
import org.deskmood.external.oauth.OauthUserProfile

data class OauthUserProfileResponse(
    val email: String,
    val nickname: String,
    val birth: String,
    val gender: String,
) {

    companion object {
        fun from(oauthUserProfile: OauthUserProfile): OauthUserProfileResponse {
            return OauthUserProfileResponse(
                oauthUserProfile.email,
                oauthUserProfile.nickname,
                DateTimeUtil.toDefaultDateFormat(oauthUserProfile.birth),
                oauthUserProfile.gender.value
            )
        }
    }
}
