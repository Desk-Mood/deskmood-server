package org.deskmood.enity.user

import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.user.User
import org.deskmood.domain.user.UserRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Repository
@Transactional
class UserEntityRepository(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {

    override fun save(user: User): Long {
        val userEntity = user.toEntity()
        userJpaRepository.save(userEntity)
        return userEntity.id
    }

    override fun findById(id: Long): User? {
        val userEntity = userJpaRepository.findById(id).getOrNull()
        return userEntity?.toCoreDomain()
    }

    override fun findByOauth(oauth: Oauth): User? {
        val userEntity = userJpaRepository.findAll(0, 1) {
            select(
                entity(UserEntity::class)
            ).from(
                entity(UserEntity::class)
            ).whereAnd(
                path(UserEntity::platform).eq(oauth.platform),
                path(UserEntity::email).eq(oauth.email)
            )
        }.firstOrNull()
        return userEntity?.toCoreDomain()
    }

    override fun existsByNickname(nickname: String): Boolean {
        val userEntity = userJpaRepository.findAll(0, 1) {
            select(
                entity(UserEntity::class)
            ).from(
                entity(UserEntity::class)
            ).whereAnd(
                path(UserEntity::nickname).eq(nickname)
            )
        }.firstOrNull()

        return userEntity?.let { true } ?: false
    }
}
