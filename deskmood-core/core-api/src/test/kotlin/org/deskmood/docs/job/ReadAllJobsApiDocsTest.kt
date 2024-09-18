package org.deskmood.docs.job

import io.mockk.every
import io.wwan13.api.document.snippets.NUMBER
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.domain.job.Job
import org.junit.jupiter.api.Test

class ReadAllJobsApiDocsTest : JobApiDocsTest() {

    @Test
    fun `직업 전체 조회 API`() {
        every { jobService.readAll() } returns listOf(
            Job(value = "개발"),
            Job(value = "경영/비즈니스"),
            Job(value = "마케팅/광고")
        )

        val api = api.get("/api/v1/job")

        documentFor(api, "get-job") {
            summary("직업 전체 조회 API")
            guide(
                "온보딩 과정에서 등록된 모든 직업을 조회하기 위한 API",
                "누락된 데이터가 있거나, 이상한 데이터가 있다면 즉시 말씀해 주세요",
                "개발, 경영/비즈니스, 마케팅/광고, 디자인, 영업, 고객서비스/리테일, 미디어, 엔지니어링/설계, HR, 게임제작, " +
                    "금융, 제조/생산, 교육, 의료/제약/바이오, 뮬류/무역, 법률/집행기관, 식/음료, 건설/시설, 공공/복지, 기타"
            )
            responseFields(
                "data.count" isTypeOf NUMBER whichMeans "전체 데이터 개수",
                "data.list[].id" isTypeOf NUMBER whichMeans "직업 id",
                "data.list[].value" isTypeOf STRING whichMeans "직업"
            )
        }
    }
}
