package org.deskmood.enity.base

import org.deskmood.domain.base.Timestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.MIN

    @LastModifiedDate
    var lastModifiedAt: LocalDateTime = LocalDateTime.MIN

    fun toTimeStamp(): Timestamp {
        return Timestamp(createdAt, lastModifiedAt)
    }
}
