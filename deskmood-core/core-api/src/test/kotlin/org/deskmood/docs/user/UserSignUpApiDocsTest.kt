package org.deskmood.docs.user

import io.mockk.every
import io.wwan13.api.document.snippets.DATE
import io.wwan13.api.document.snippets.ENUM
import io.wwan13.api.document.snippets.NUMBER
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.hasValues
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.controller.user.dto.UserAppendRequest
import org.deskmood.docs.expectedErrorTypes
import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.error.InUsedNickname
import org.deskmood.domain.error.InvalidEnumValue
import org.deskmood.domain.error.UserAlreadyRegistered
import org.deskmood.domain.user.Gender
import org.deskmood.error.InvalidNickname
import org.junit.jupiter.api.Test

class UserSignUpApiDocsTest : UserApiDocsTest() {

    @Test
    fun `사용자 회원 가입 API`() {
        every { userService.appendUser(any(), any()) } returns 1L

        val api = api.post("/api/v1/user") {
            requestBody(
                UserAppendRequest(
                    platform = "naver",
                    email = "wwan13@naver.com",
                    nickname = "kim",
                    birth = "2001-02-22",
                    gender = "남성",
                    job = "개발자"
                )
            )
        }

        documentFor(api, "user-sign-up") {
            summary("사용자 회원 가입 API")
            guide(
                "사전에 호출한 oauth2 기본 프로필 조회 결과를 담아 회원가입 API를 호출해 주세요.",
            )
            containedEnums(
                "platform" hasValues OauthPlatform.entries.map { it.value },
                "gender" hasValues Gender.entries.map { it.value }
            )
            expectedErrorTypes(
                InUsedNickname,
                InvalidNickname,
                UserAlreadyRegistered,
                InvalidEnumValue()
            )
            requestFields(
                "platform" isTypeOf ENUM(OauthPlatform::class) whichMeans "oauth2 플랫폼 (naver/google)",
                "email" isTypeOf STRING whichMeans "이메일",
                "nickname" isTypeOf STRING whichMeans "닉네임",
                "birth" isTypeOf DATE whichMeans "생일",
                "gender" isTypeOf ENUM(Gender::class) whichMeans "성별 (남성/여성)",
                "job" isTypeOf STRING whichMeans "직업",
            )
            responseFields(
                "data.id" isTypeOf NUMBER whichMeans "생성된 유저의 ID"
            )
        }
    }
}
