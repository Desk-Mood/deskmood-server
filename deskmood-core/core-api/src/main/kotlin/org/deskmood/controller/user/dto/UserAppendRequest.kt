package org.deskmood.controller.user.dto

import org.deskmood.datetime.DateTimeUtil
import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.base.ValueEnum
import org.deskmood.domain.job.UserJob
import org.deskmood.domain.user.Gender
import org.deskmood.domain.user.UserProfile
import org.deskmood.error.InvalidNickname
import org.deskmood.validator.requestvalidator.RequestValidator.validate

data class UserAppendRequest(
    val platform: String,
    val email: String,
    val nickname: String,
    val birth: String,
    val gender: String,
    val jobIds: List<Long>
) {

    init {
        validate(InvalidNickname) {
            val regex = Regex("^[가-힣a-zA-Z]{2,10}$")
            regex.matches(nickname)
        }
    }

    fun toOauth(): Oauth {
        return Oauth(ValueEnum.resolve<OauthPlatform>(platform), email)
    }

    fun toUserProfile(): UserProfile {
        return UserProfile(
            nickname,
            DateTimeUtil.toLocalDate(birth),
            ValueEnum.resolve<Gender>(gender)
        )
    }

    fun toUserJobs(userId: Long): List<UserJob> {
        return jobIds.map {
            UserJob(userId = userId, jobId = it)
        }
    }
}
