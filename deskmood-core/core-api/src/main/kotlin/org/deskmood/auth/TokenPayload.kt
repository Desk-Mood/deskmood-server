package org.deskmood.auth

import io.wwan13.wintersecurity.jwt.payload.annotation.Payload
import io.wwan13.wintersecurity.jwt.payload.annotation.Roles
import io.wwan13.wintersecurity.jwt.payload.annotation.Subject
import org.deskmood.domain.user.UserRole

@Payload
data class TokenPayload(
    @Subject
    val userId: Long,
    @Roles
    val role: UserRole
)
