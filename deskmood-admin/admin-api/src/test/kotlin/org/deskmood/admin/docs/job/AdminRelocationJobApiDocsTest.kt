package org.deskmood.admin.docs.job

import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.wwan13.api.document.snippets.NULL
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.junit.jupiter.api.Test

class AdminRelocationJobApiDocsTest : AdminJobApiDocsTest() {

    @Test
    fun `job의 정렬 순서를 재배열 하는 API`() {
        every { adminJobService.relocationOrder(any(), any()) } just runs

        val api = api.patch("/api/admin/v1/job/{jobId}/relocation/{to}", 1, 3)

        documentFor(api, "admin-relocation-jobs") {
            summary("job 정렬 순서 변경 및 재배치 API")
            guide(
                "온보딩 창의 직업 정렬 순서를 변경하는 API"
            )
            pathParameters(
                "jobId" whichMeans "순서를 변경하려는 job의 id",
                "to" whichMeans "변경하려는 순서"
            )
            responseFields(
                "data" isTypeOf NULL whichMeans "데이터"
            )
        }
    }
}
