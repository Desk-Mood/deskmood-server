package org.deskmood.admin.domain.job

import org.deskmood.admin.domain.base.AdminBaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "job")
class AdminJob(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    var id: Long = 0,

    var value: String,

    var order: Int
) : AdminBaseEntity()
