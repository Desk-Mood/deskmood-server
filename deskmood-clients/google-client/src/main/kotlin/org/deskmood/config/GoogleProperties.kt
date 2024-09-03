package org.deskmood.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("google")
class GoogleProperties(
    val clientId: String,
    val clientSecret: String
)
