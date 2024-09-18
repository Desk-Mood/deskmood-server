package org.deskmood.enity.userjob

import org.deskmood.domain.job.UserJob

fun UserJob.toEntity(): UserJobEntity {
    return UserJobEntity(id, userId, jobId)
}

fun UserJobEntity.toCoreDomain(): UserJob {
    return UserJob(id, toTimeStamp(), userId, jobId)
}
