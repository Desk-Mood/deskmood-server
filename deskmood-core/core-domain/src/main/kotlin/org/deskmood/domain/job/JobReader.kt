package org.deskmood.domain.job

import org.deskmood.domain.error.NoSuchData
import org.deskmood.error.DeskmoodException
import org.springframework.stereotype.Component

@Component
class JobReader(
    private val jobRepository: JobRepository
) {

    fun read(id: Long): Job {
        return jobRepository.findById(id)
            ?: throw DeskmoodException(NoSuchData(Job::class, id))
    }

    fun readAll(): List<Job> {
        return jobRepository.findAll()
    }
}
