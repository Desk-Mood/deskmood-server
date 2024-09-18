package org.deskmood.domain.job

import org.springframework.stereotype.Service

@Service
class JobService(
    private val jobReader: JobReader
) {

    fun readAll(): List<Job> {
        return jobReader.readAll()
    }
}
