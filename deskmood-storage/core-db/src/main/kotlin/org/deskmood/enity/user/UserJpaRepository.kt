package org.deskmood.enity.user

import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository :
    JpaRepository<UserEntity, Long>, KotlinJdslJpqlExecutor
