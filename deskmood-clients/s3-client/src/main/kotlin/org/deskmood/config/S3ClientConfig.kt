package org.deskmood.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan
class S3ClientConfig(
    private val s3Properties: S3Properties
) {

    @Bean
    fun awsCredentialsProvider(): BasicAWSCredentials {
        return BasicAWSCredentials(s3Properties.accessKey, s3Properties.secretKey)
    }

    @Bean
    fun amazonS3(): AmazonS3 {
        return AmazonS3ClientBuilder.standard()
            .withRegion(AWS_REGION)
            .withCredentials(AWSStaticCredentialsProvider(awsCredentialsProvider()))
            .build()
    }

    companion object {
        const val AWS_REGION = "ap-northeast-2"
    }
}
