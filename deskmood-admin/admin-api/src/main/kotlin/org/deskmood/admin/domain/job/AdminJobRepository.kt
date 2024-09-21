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

    fun findOrder(id: Long): Int? {
        return jpqlExecutor.find {
            select(
                path(AdminJob::sortOrder)
            ).from(
                entity(AdminJob::class)
            ).where(
                path(AdminJob::id).eq(id)
            )
        }
    }

    fun count(): Long {
        return jobJpaRepository.count()
    }

    @Transactional
    fun modifyOrder(id: Long, to: Int): Int {
        return jpqlExecutor.update {
            update(
                entity(AdminJob::class)
            ).set(
                path(AdminJob::sortOrder), to
            ).whereAnd(
                path(AdminJob::id).eq(id),
            )
        }
    }

    @Transactional
    fun increaseSortOrderBetweenExcludeTarget(
        target: Long,
        start: Int,
        end: Int
    ) {
        jpqlExecutor.update {
            update(
                entity(AdminJob::class)
            ).set(
                path(AdminJob::sortOrder), plus(path(AdminJob::sortOrder), 1)
            ).whereAnd(
                path(AdminJob::sortOrder).between(start, end),
                path(AdminJob::id).notEqual(target)
            )
        }
    }

    @Transactional
    fun decreaseSortOrderBetweenExcludeTarget(
        target: Long,
        start: Int,
        end: Int
    ) {
        jpqlExecutor.update {
            update(
                entity(AdminJob::class)
            ).set(
                path(AdminJob::sortOrder), minus(path(AdminJob::sortOrder), 1)
            ).whereAnd(
                path(AdminJob::sortOrder).between(start, end),
                path(AdminJob::id).notEqual(target)
            )
        }
    }

    @Transactional
    fun deleteAll() {
        jobJpaRepository.deleteAll()
    }
}
