package org.deskmood.domain.user

import java.time.LocalDate

data class UserProfile(
    val nickname: String,
    val birth: LocalDate,
    val gender: Gender,
    val job: String
)
