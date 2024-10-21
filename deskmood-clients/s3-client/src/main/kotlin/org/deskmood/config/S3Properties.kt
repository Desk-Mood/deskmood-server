package org.deskmood.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("cloud.aws.s3")
data class S3Properties(
    val bucket: String,
    val accessKey: String,
    val secretKey: String
)
