package org.deskmood.admin.domain.job

import org.deskmood.admin.error.AdminAlreadyHasJobData
import org.deskmood.admin.error.AdminNoSuchData
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

    fun relocationOrder(jobId: Long, to: Int) {
        val originOrder = jobRepository.findOrder(jobId)
            ?: throw DeskmoodException(AdminNoSuchData(AdminJob::class, jobId))

        when (compareValues(to, originOrder)) {
            1 -> {
                jobRepository.modifyOrder(jobId, to)
                jobRepository.decreaseSortOrderBetweenExcludeTarget(jobId, originOrder, to)
            }

            -1 -> {
                jobRepository.modifyOrder(jobId, to)
                jobRepository.increaseSortOrderBetweenExcludeTarget(jobId, to, originOrder)
            }
        }
    }

    fun removeAll() {
        jobRepository.deleteAll()
    }
}
