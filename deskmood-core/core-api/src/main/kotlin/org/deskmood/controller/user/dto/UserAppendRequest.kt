package org.deskmood.controller.user.dto

import org.deskmood.datetime.DateTimeUtil
import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.base.ValueEnum
import org.deskmood.domain.user.Gender
import org.deskmood.domain.user.UserProfile

data class UserAppendRequest(
    val platform: String,
    val email: String,
    val nickname: String,
    val birth: String,
    val gender: String,
    val job: String
) {

    fun toOauth(): Oauth {
        return Oauth(ValueEnum.resolve<OauthPlatform>(platform), email)
    }

    fun toUserProfile(): UserProfile {
        return UserProfile(
            nickname,
            DateTimeUtil.toLocalDate(birth),
            ValueEnum.resolve<Gender>(gender),
            job
        )
    }
}
