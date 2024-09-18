package org.deskmood.domain.user

import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.error.InUsedNickname
import org.deskmood.domain.error.UserAlreadyRegistered
import org.deskmood.error.DeskmoodException
import org.springframework.stereotype.Component

@Component
class UserValidator(
    private val userRepository: UserRepository
) {

    fun validateIsAvailableNickname(nickname: String) {
        if (userRepository.existsByNickname(nickname)) {
            throw DeskmoodException(InUsedNickname)
        }
    }

    fun validateAlreadyRegistered(oauth: Oauth) {
        userRepository.findByOauth(oauth)?.let {
            throw DeskmoodException(UserAlreadyRegistered)
        }
    }
}
