package org.deskmood.domain.user

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userReader: UserReader,
) {

    fun isAvailableNickname(nickname: String): Boolean {
        return !userReader.isInUseNickname(nickname)
    }
}
