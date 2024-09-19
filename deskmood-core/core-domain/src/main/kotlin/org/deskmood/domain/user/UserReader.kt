package org.deskmood.domain.user

import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.error.NoSuchData
import org.deskmood.error.DeskmoodException
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    fun readByOauth(oauth: Oauth): User {
        return userRepository.findByOauth(oauth)
            ?: throw DeskmoodException(NoSuchData(User::class, oauth.email))
    }

    fun isInUseNickname(nickname: String): Boolean {
        return userRepository.findByNickname(nickname)?.let { true } ?: false
    }
}
