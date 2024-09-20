package org.deskmood.enity.job

import org.deskmood.domain.job.Job
import org.deskmood.domain.job.JobRepository
import org.deskmood.enity.base.JpqlExecutor
import org.deskmood.optional.mapOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class JobEntityRepository(
    private val jobJpaRepository: JobJpaRepository,
    private val jpqlExecutor: JpqlExecutor
) : JobRepository {

    override fun findById(id: Long): Job? {
        val job = jobJpaRepository.findById(id)
        return job.mapOrNull { it.toCoreDomain() }
    }

    override fun findAll(): List<Job> {
        jpqlExecutor.findAll {
            select(
                entity(JobEntity::class)
            ).from(
                entity(JobEntity::class)
            ).orderBy(
                path(JobEntity::sortOrder).asc()
            )
        }
        val jobs = jobJpaRepository.findAll()
        return jobs.map { it.toCoreDomain() }
    }
}
