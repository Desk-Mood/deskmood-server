package org.deskmood.google.user

import java.time.LocalDate

class GoogleUserProfile(
    val id: String,
    val name: String,
    val profileImage: String,
    val email: String,
    val gender: String,
    val birth: LocalDate
)
