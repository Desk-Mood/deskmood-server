package org.deskmood.docs.file

import io.mockk.every
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.hasValues
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.FileType
import org.deskmood.controller.file.dto.PreSignedUrlRequest
import org.deskmood.docs.expectedErrorTypes
import org.deskmood.error.NotSupportedFileType
import org.junit.jupiter.api.Test

class GeneratePreSignedUrlApiDocsTest : FileApiDocsTest() {

    @Test
    fun `preSignedUrl을 생성하는 API`() {

        every { s3Client.providePreSignedUrl(any(), any()) } returns "https://pre-signed-url.com"

        val request = PreSignedUrlRequest(
            fileName = "file_name.png",
            fileType = "image"
        )

        val api = api.post("/api/v1/file/preSignedUrl") {
            requestBody(request)
        }

        documentFor(api, "provide-pre-sighed-url") {
            summary("pre signed url을 생성하는 API")
            guide(
                "pre signed url을 생성하는 API 입니다.",
                "생성된 url로 업로드 하려는 파일을 업로드 해주세요",
                "여기서 제공받은 pre signed url을 추후 file url 필드에 포함하면 됩니다.",
                "pre signed url가 처음이라면 아래 블로그를 참고해 주세요.",
                "https://omoknooni.tistory.com/136",
                "",
                "간단한 흐름 정리",
                "1. 해당 API를 호출해 pre signed url 생성",
                "2. 생성된 pre signed url 에 PUT 요청을 보내 amazon s3에 파일을 직접 업로드",
                "3. fileUrl을 요구하는 API에 여기서 생성된 pre signed url을 그대로 제공",
                "3-1. 혹은 pre signed url의 ? 이후(쿼리 파라미터)를 제거한 url만 제공하여도 무방함"
            )
            expectedErrorTypes(
                NotSupportedFileType
            )
            containedEnums(
                "fileType" hasValues FileType.entries.map { it.value }
            )
            requestFields(
                "fileName" isTypeOf STRING whichMeans "파일 원본 이름",
                "fileType" isTypeOf STRING whichMeans "파일의 타입",
            )
            responseFields(
                "data.preSignedUrl" isTypeOf STRING whichMeans "생성된 pre signed url"
            )
        }
    }
}
