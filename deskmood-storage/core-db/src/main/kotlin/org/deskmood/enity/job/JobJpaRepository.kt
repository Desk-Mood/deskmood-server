package org.deskmood.enity.job

import org.springframework.data.jpa.repository.JpaRepository

interface JobJpaRepository : JpaRepository<JobEntity, Long>
