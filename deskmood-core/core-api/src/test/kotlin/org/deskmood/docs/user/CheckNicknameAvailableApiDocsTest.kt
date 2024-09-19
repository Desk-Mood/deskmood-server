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
            guide(
                "온보딩 과정에서 닉네임 중복을 확인하는 API",
                "사용 가능한 닉네임인 경우 `true` / 중복되어 사용할 수 없는 닉네임인 경우 `false`룰 반환합니다."
            )
            queryParameters(
                "nickname" whichMeans "확인 하려는 닉네임"
            )
            responseFields(
                "data.result" isTypeOf BOOLEAN whichMeans "닉네임 중복 확인 결과"
            )
        }
    }
}
