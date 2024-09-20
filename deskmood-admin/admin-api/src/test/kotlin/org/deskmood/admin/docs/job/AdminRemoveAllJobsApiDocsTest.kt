package org.deskmood.admin.docs.job

import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.wwan13.api.document.snippets.NULL
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.junit.jupiter.api.Test

class AdminRemoveAllJobsApiDocsTest : AdminJobApiDocsTest() {

    @Test
    fun `등록된 모든 직업을 삭제하는 API`() {
        every { adminJobService.removeAll() } just runs

        val api = api.delete("/api/admin/v1/job")

        documentFor(api, "admin-remove-all-jobs") {
            summary("등록된 모든 직업을 삭제하는 API")
            guide(
                "등록된 모든 직업을 삭제하는 API",
            )
            responseFields(
                "data" isTypeOf NULL whichMeans "데이터"
            )
        }
    }
}
