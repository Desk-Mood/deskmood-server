package org.deskmood.enity.user

import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.user.User
import org.deskmood.domain.user.UserProfile

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        nickname = profile.nickname,
        birth = profile.birth,
        gender = profile.gender,
        job = profile.job,
        role = role,
        platform = oauth.platform,
        email = oauth.email,
    )
}

fun UserEntity.toCoreDomain(): User {
    return User(
        id = id,
        timestamp = toTimeStamp(),
        role = role,
        oauth = Oauth(
            platform = platform,
            email = email
        ),
        profile = UserProfile(
            nickname = nickname,
            birth = birth,
            gender = gender,
            job = job,
        )
    )
}
