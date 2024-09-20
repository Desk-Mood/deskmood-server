package org.deskmood.admin.domain.job

import org.deskmood.admin.domain.base.AdminJpqlExecutor
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class AdminJobRepository(
    private val jobJpaRepository: AdminJobJpaRepository,
    private val jpqlExecutor: AdminJpqlExecutor
) {

    @Transactional
    fun saveAll(jobs: List<AdminJob>) {
        jobJpaRepository.saveAll(jobs)
    }

    fun readAll(): List<AdminJob> {
        return jpqlExecutor.findAll {
            select(
                entity(AdminJob::class)
            ).from(
                entity(AdminJob::class)
            ).orderBy(
                path(AdminJob::sortOrder).asc()
            )
        }
    }

    fun count(): Long {
        return jobJpaRepository.count()
    }

    @Transactional
    fun deleteAll() {
        jobJpaRepository.deleteAll()
    }
}
