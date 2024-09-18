package org.deskmood.config

import com.linecorp.kotlinjdsl.render.RenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["org.deskmood"])
@EnableJpaRepositories(basePackages = ["org.deskmood"])
@EnableJpaAuditing
class CoreDbConfig {

    @Bean
    fun jpqlReaderContext(): RenderContext {
        return JpqlRenderContext()
    }
}
