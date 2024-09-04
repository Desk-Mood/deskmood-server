package org.deskmood.domain.user

import org.deskmood.domain.auth.Oauth

interface UserRepository {
    fun save(user: User): Long

    fun findById(id: Long): User?

    fun findByOauth(oauth: Oauth): User?
}
