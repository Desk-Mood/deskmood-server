package org.deskmood.domain.job

import org.deskmood.domain.base.Timestamp

data class UserJob(
    val id: Long = 0,
    val timestamp: Timestamp = Timestamp(),
    val userId: Long,
    val jobId: Long
)
