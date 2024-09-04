package org.deskmood.domain.base

import org.deskmood.datetime.DateTimePicker
import java.time.LocalDateTime

data class Timestamp(
    val createdAt: LocalDateTime = DateTimePicker.now(),
    val lastModifiedAt: LocalDateTime = DateTimePicker.now()
)
