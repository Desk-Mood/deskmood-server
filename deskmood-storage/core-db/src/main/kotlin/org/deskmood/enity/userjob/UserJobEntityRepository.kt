package org.deskmood.enity.userjob

import org.deskmood.domain.job.UserJob
import org.deskmood.domain.job.UserJobRepository
import org.deskmood.enity.base.JpqlExecutor
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class UserJobEntityRepository(
    private val userJobJpaRepository: UserJobJpaRepository,
    private val jpqlExecutor: JpqlExecutor
) : UserJobRepository {

    @Transactional
    override fun save(userJob: UserJob): Long {
        val userJobEntity = userJob.toEntity()
        userJobJpaRepository.save(userJobEntity)

        return userJobEntity.id
    }
}
