package org.deskmood

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.Headers
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import org.deskmood.config.S3Properties
import org.deskmood.datetime.DateTimePicker
import org.springframework.stereotype.Component
import java.util.Base64
import java.util.Date

@Component
class S3Client(
    private val s3Properties: S3Properties,
    private val amazonS3: AmazonS3
) {

    fun providePreSignedUrl(
        fileType: FileType,
        fileName: String
    ): String {
        val filePath = "${fileType.value}/${provideEncodedFileName(fileName)}"
        val request = GeneratePresignedUrlRequest(s3Properties.bucket, filePath).apply {
            withMethod(HttpMethod.PUT)
            withExpiration(calculateExpiration())
        }
        request.addRequestParameter(
            Headers.S3_CANNED_ACL,
            CannedAccessControlList.PublicRead.toString()
        )

        val preSighedUrl = amazonS3.generatePresignedUrl(request)
        return preSighedUrl.toString()
    }

    private fun calculateExpiration(): Date {
        return Date(Date().time + (1000 * 60 * 2).toLong())
    }

    private fun provideEncodedFileName(fileName: String): String {
        val fileNameWithDate = "$fileName-${DateTimePicker.now()}"
        val encoded = Base64.getEncoder().encodeToString(fileNameWithDate.toByteArray())

        return "$encoded.${extractFileExtension(fileName)}"
    }

    private fun extractFileExtension(fileName: String): String {
        return fileName.split(".").last()
    }

    fun parsePreSignedUrl(preSignedUrl: String): String {
        return preSignedUrl.split("?").first()
    }
}
