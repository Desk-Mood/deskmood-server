package org.deskmood.external.oauth

import org.deskmood.domain.user.Gender
import java.time.LocalDate

data class OauthUserProfile(
    val email: String,
    val nickname: String,
    val birth: LocalDate,
    val gender: Gender,
)
