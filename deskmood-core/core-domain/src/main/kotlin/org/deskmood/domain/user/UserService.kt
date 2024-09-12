package org.deskmood.domain.user

import org.deskmood.domain.auth.Oauth
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userAppender: UserAppender,
    private val userReader: UserReader,
    private val userValidator: UserValidator,
) {

    fun appendUser(
        oauth: Oauth,
        profile: UserProfile
    ): Long {
        userValidator.validateIsAvailableNickname(profile.nickname)
        val user = User(oauth = oauth, profile = profile)
        return userAppender.append(user)
    }

    fun isAvailableNickname(nickname: String): Boolean {
        return !userReader.isInUseNickname(nickname)
    }
}
