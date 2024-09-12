package org.deskmood.enity.user

import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.user.User
import org.deskmood.domain.user.UserProfile

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        email = profile.email,
        nickname = profile.nickname,
        birth = profile.birth,
        gender = profile.gender,
        job = profile.job,
        role = role,
        platform = oauth.platform,
        platformIdentifier = oauth.identifier
    )
}

fun UserEntity.toCoreDomain(): User {
    return User(
        id = id,
        timestamp = toTimeStamp(),
        role = role,
        oauth = Oauth(
            platform = platform,
            identifier = platformIdentifier
        ),
        profile = UserProfile(
            email = email,
            nickname = nickname,
            birth = birth,
            gender = gender,
            job = job,
        )
    )
}
