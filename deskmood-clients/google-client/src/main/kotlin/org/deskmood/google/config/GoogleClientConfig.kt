package org.deskmood.google.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan
@EnableFeignClients(
    basePackages = ["org.deskmood.google"]
)
class GoogleClientConfig
