package org.deskmood.admin.domain.job

import org.deskmood.admin.error.AdminAlreadyHasJobData
import org.deskmood.error.DeskmoodException
import org.springframework.stereotype.Service

@Service
class AdminJobService(
    private val jobRepository: AdminJobRepository
) {

    fun setup() {
        if (jobRepository.count() > 0) {
            throw DeskmoodException(AdminAlreadyHasJobData)
        }
        val jobs = AdminDefaultJob.entries.map { it.toAdminJob() }
        jobRepository.saveAll(jobs)
    }

    fun readAll(): List<AdminJob> {
        return jobRepository.readAll()
    }

    fun removeAll() {
        jobRepository.deleteAll()
    }
}
