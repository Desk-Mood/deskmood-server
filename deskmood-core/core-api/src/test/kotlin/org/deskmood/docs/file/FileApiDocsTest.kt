package org.deskmood.docs.file

import com.ninjasquad.springmockk.MockkBean
import org.deskmood.S3Client
import org.deskmood.controller.file.FileController
import org.deskmood.docs.ApiDocsTest
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [FileController::class])
abstract class FileApiDocsTest : ApiDocsTest("file") {

    @MockkBean
    lateinit var s3Client: S3Client
}
