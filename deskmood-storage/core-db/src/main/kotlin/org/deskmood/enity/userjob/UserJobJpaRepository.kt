package org.deskmood.enity.userjob

import org.springframework.data.jpa.repository.JpaRepository

interface UserJobJpaRepository : JpaRepository<UserJobEntity, Long>
