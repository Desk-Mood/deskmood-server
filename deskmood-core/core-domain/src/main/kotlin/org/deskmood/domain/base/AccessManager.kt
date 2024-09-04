package org.deskmood.domain.base

import org.deskmood.domain.error.AccessFailed
import org.deskmood.error.DeskmoodException
import org.springframework.stereotype.Component

@Component
class AccessManager {

    fun ownerOnly(
        target: Ownable,
        accessorId: Long
    ) {
        if (!target.isOwner(accessorId)) {
            throw DeskmoodException(AccessFailed)
        }
    }
}
