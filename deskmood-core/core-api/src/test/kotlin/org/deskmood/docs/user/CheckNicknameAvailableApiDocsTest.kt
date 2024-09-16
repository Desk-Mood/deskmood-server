package org.deskmood.docs.user

import io.mockk.every
import io.wwan13.api.document.snippets.BOOLEAN
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.junit.jupiter.api.Test

class CheckNicknameAvailableApiDocsTest : UserApiDocsTest() {

    @Test
    fun `닉네임 중복 확인 API`() {
        every { userService.isAvailableNickname(any()) } returns true

        val api = api.get("/api/v1/user/nickname/available") {
            queryParam("nickname", "nickname")
        }

        documentFor(api, "check-nickname-available") {
            summary("닉네임 중복 확인 APi")
            queryParameters(
                "nickname" whichMeans "확인 하려는 닉네임"
            )
            responseFields(
                "data.result" isTypeOf BOOLEAN whichMeans "닉네임 중복 확인 결과"
            )
        }
    }
}
