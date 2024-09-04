package org.deskmood.domain.base

import java.time.LocalDateTime

data class Timestamp(
    val createdAt: LocalDateTime,
    val lastModifiedAt: LocalDateTime
)
