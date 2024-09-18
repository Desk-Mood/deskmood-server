package org.deskmood.enity.userjob

import org.deskmood.enity.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_job")
class UserJobEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_job_id")
    var id: Long = 0,

    val userId: Long,

    val jobId: Long
) : BaseEntity()
