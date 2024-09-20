package org.deskmood.admin.docs.job

import io.mockk.every
import io.wwan13.api.document.snippets.NUMBER
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.admin.domain.job.AdminJob
import org.junit.jupiter.api.Test

class AdminReadAllJobsApiDocsTest : AdminJobApiDocsTest() {

    @Test
    fun `모든 직업 조회 API`() {
        every { adminJobService.readAll() } returns listOf(
            AdminJob(1, "개발", 1),
            AdminJob(1, "경영/비즈니스", 1),
            AdminJob(1, "마케팅/광고", 1),
            AdminJob(1, "디자인", 1),
            AdminJob(1, "영업", 1)
        )

        val api = api.get("/api/admin/v1/job")

        documentFor(api, "admin-read-all-job") {
            summary("직업 전체 조회 API")
            guide(
                "현재 등록되어 있는 모든 직업을 조회하는 API"
            )
            responseFields(
                "data.count" isTypeOf NUMBER whichMeans "전체 데이터 개수",
                "data.list[].id" isTypeOf NUMBER whichMeans "직업 id",
                "data.list[].value" isTypeOf STRING whichMeans "직업",
                "data.list[].order" isTypeOf NUMBER whichMeans "순서"
            )
        }
    }
}
