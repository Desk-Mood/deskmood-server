package org.deskmood.domain.user

import org.springframework.stereotype.Component

@Component
class UserAppender(
    private val userRepository: UserRepository
) {

    fun append(user: User): Long {
        return userRepository.save(user)
    }
}
