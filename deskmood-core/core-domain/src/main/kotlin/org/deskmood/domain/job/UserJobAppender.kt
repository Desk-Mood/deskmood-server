package org.deskmood.domain.job

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserJobAppender(
    private val userJobRepository: UserJobRepository
) {

    @Transactional
    fun appendAll(userJobs: List<UserJob>) {
        userJobs.forEach { userJobRepository.save(it) }
    }
}
