package org.deskmood.domain.user

import org.deskmood.domain.error.InUsedNickname
import org.deskmood.error.DeskmoodException
import org.springframework.stereotype.Component

@Component
class UserValidator(
    private val userReader: UserReader,
) {

    fun validateIsAvailableNickname(nickname: String) {
        if (userReader.isInUseNickname(nickname)) {
            throw DeskmoodException(InUsedNickname)
        }
    }
}
