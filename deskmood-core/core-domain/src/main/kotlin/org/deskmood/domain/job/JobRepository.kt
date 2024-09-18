package org.deskmood.domain.job

interface JobRepository {
    fun findById(id: Long): Job?

    fun findAll(): List<Job>
}
