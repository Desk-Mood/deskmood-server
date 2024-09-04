package org.deskmood.enity.user

import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.user.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        job = job,
        role = role,
        platform = oauth.platform,
        platformIdentifier = oauth.identifier
    )
}

fun UserEntity.toCoreDomain(): User {
    return User(
        id = id,
        timestamp = toTimeStamp(),
        oauth = Oauth(
            platform = platform,
            identifier = platformIdentifier
        ),
        name = name,
        job = job,
        role = role
    )
}
