package org.deskmood.admin.domain.base

import com.linecorp.kotlinjdsl.dsl.jpql.Jpql
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.querymodel.jpql.JpqlQueryable
import com.linecorp.kotlinjdsl.querymodel.jpql.select.SelectQuery
import com.linecorp.kotlinjdsl.render.RenderContext
import com.linecorp.kotlinjdsl.support.hibernate.extension.createQuery
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
class AdminJpqlExecutor(
    private val entityManager: EntityManager,
    private val renderContext: RenderContext
) {

    @Transactional
    fun <T : Any> find(
        init: Jpql.() -> JpqlQueryable<SelectQuery<T>>
    ): T? {
        return entityManager
            .createQuery(jpql(init), renderContext)
            .apply {
                firstResult = 0
                maxResults = 1
            }
            .resultList
            .firstOrNull()
    }

    fun <T : Any> findAll(
        init: Jpql.() -> JpqlQueryable<SelectQuery<T>>
    ): List<T> {
        return entityManager
            .createQuery(jpql(init), renderContext)
            .resultList
            .filterNotNull()
    }

    fun <T : Any> findPage(
        offset: Int,
        limit: Int,
        init: Jpql.() -> JpqlQueryable<SelectQuery<T>>
    ): List<T> {
        return entityManager
            .createQuery(jpql(init), renderContext)
            .apply {
                firstResult = offset
                maxResults = limit
            }
            .resultList
            .filterNotNull()
    }
}
