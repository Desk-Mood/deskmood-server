package org.deskmood.domain.user

import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    fun isInUseNickname(nickname: String): Boolean {
        return userRepository.existsByNickname(nickname)
    }
}
