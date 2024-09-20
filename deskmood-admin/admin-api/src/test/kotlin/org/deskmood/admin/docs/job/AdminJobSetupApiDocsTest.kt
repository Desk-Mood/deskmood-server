package org.deskmood.admin.docs.job

import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.wwan13.api.document.snippets.NULL
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.admin.docs.expectedErrorTypes
import org.deskmood.admin.error.AdminAlreadyHasJobData
import org.junit.jupiter.api.Test

class AdminJobSetupApiDocsTest : AdminJobApiDocsTest() {

    @Test
    fun `기본 직업을 추가하는 API`() {
        every { adminJobService.setup() } just runs

        val api = api.post("/api/admin/v1/job/setup")

        documentFor(api, "admin-job-setup") {
            summary("기본 직업 정보를 설정하는 API")
            guide(
                "온보딩에 활용될 기본 직업 정보를 세팅하는 API",
                "개발, 경영/비즈니스, 마케팅/광고, 디자인, 영업, 고객서비스/리테일, 미디어, 엔지니어링/설계, " +
                    "HR, 게임제작, 금융, 제조/생산, 교육, 의료/제약/바이오, 뮬류/무역, 법률/집행기관, 식/음료, 건설/시설, 공공/복지, 기타"
            )
            expectedErrorTypes(
                AdminAlreadyHasJobData
            )
            responseFields(
                "data" isTypeOf NULL whichMeans "데이터"
            )
        }
    }
}
