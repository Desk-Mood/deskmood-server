package org.deskmood.domain.job

import org.springframework.stereotype.Service

@Service
class UserJobService(
    private val userJobAppender: UserJobAppender
) {

    fun appendAll(userJobs: List<UserJob>) {
        userJobAppender.appendAll(userJobs)
    }
}
