package org.deskmood.enity.user

import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.user.User
import org.deskmood.domain.user.UserRepository
import org.deskmood.enity.base.JpqlExecutor
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Repository
@Transactional(readOnly = true)
class UserEntityRepository(
    private val userJpaRepository: UserJpaRepository,
    private val jpqlExecutor: JpqlExecutor
) : UserRepository {

    @Transactional
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
        val userEntity = jpqlExecutor.find {
            select(
                entity(UserEntity::class)
            ).from(
                entity(UserEntity::class)
            ).whereAnd(
                path(UserEntity::platform).eq(oauth.platform),
                path(UserEntity::email).eq(oauth.email)
            )
        }
        return userEntity?.toCoreDomain()
    }

    override fun findByNickname(nickname: String): User? {
        val userEntity = jpqlExecutor.find {
            select(
                entity(UserEntity::class)
            ).from(
                entity(UserEntity::class)
            ).whereAnd(
                path(UserEntity::nickname).eq(nickname)
            )
        }

        return userEntity?.toCoreDomain()
    }
}
