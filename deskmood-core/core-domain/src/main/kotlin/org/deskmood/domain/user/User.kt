package org.deskmood.domain.user

import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.base.Timestamp

data class User(
    val id: Long = 0,
    val timestamp: Timestamp = Timestamp(),
    val role: UserRole = UserRole.ROLE_USER,
    val oauth: Oauth,
    val profile: UserProfile,
)
