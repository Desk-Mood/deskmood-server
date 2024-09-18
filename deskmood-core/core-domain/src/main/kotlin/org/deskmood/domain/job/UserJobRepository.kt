package org.deskmood.domain.job

interface UserJobRepository {

    fun save(userJob: UserJob): Long
}
