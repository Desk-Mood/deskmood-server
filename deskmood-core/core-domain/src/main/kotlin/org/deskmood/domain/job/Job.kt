package org.deskmood.domain.job

import org.deskmood.domain.base.Timestamp

data class Job(
    val id: Long = 0,
    val timestamp: Timestamp = Timestamp(),
    val value: String
)
