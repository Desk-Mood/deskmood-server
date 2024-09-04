package org.deskmood.enity.user

import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.user.UserRole
import org.deskmood.enity.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "member")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long = 0,

    var name: String,

    var job: String,

    @Enumerated(EnumType.STRING)
    var role: UserRole,

    @Enumerated(EnumType.STRING)
    var platform: OauthPlatform,

    var platformIdentifier: String
) : BaseEntity()
