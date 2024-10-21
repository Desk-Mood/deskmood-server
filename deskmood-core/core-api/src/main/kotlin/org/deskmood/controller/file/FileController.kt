package org.deskmood.controller.file

import org.deskmood.FileType
import org.deskmood.S3Client
import org.deskmood.api.ApiResponse
import org.deskmood.controller.file.dto.PreSignedUrlRequest
import org.deskmood.controller.file.dto.PreSignedUrlResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/file")
class FileController(
    private val s3Client: S3Client
) {

    @PostMapping("/preSignedUrl")
    fun providePreSignedUrl(
        @RequestBody request: PreSignedUrlRequest
    ): ApiResponse<PreSignedUrlResponse> {
        val preSignedUrl =
            s3Client.providePreSignedUrl(FileType.resolve(request.fileType), request.fileName)
        val response = PreSignedUrlResponse(preSignedUrl)

        return ApiResponse.success(response)
    }
}
