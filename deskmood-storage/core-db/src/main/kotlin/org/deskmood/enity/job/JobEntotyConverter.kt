package org.deskmood.enity.job

import org.deskmood.domain.job.Job

fun JobEntity.toCoreDomain(): Job {
    return Job(id, toTimeStamp(), value)
}
