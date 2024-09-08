package org.deskmood.user

import java.time.LocalDate

data class NaverUserProfile(
    val id: String,
    val name: String,
    val profileImage: String,
    val email: String,
    val gender: String,
    val birth: LocalDate
)
