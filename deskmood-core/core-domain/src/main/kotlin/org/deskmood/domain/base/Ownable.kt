package org.deskmood.domain.base

interface Ownable {
    fun isOwner(accessorId: Long): Boolean
}
