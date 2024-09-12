package org.deskmood.naver.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("naver")
data class NaverProperties(
    val clientId: String,
    val clientSecret: String
)
