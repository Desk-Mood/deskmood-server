package org.deskmood.domain.user

import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.base.Timestamp

data class User(
    val id: Long = 0,
    val timestamp: Timestamp = Timestamp(),
    val oauth: Oauth,
    val name: String,
    val role: UserRole,
    val job: String
)
