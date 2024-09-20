package org.deskmood.admin.domain.job

import org.springframework.data.jpa.repository.JpaRepository

interface AdminJobJpaRepository : JpaRepository<AdminJob, Long>
