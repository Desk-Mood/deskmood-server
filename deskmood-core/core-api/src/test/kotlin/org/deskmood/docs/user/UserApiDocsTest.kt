package org.deskmood.docs.user

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.wwan13.api.document.snippets.BOOLEAN
import io.wwan13.api.document.snippets.DATE
import io.wwan13.api.document.snippets.ENUM
import io.wwan13.api.document.snippets.NUMBER
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.controller.user.UserController
import org.deskmood.controller.user.dto.UserAppendRequest
import org.deskmood.docs.ApiDocsTest
import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.user.Gender
import org.deskmood.domain.user.UserService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [UserController::class])
class UserApiDocsTest(
    private val userController: UserController
) : ApiDocsTest(userController, "user") {

    @MockkBean
    private lateinit var userService: UserService

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
            summary("사용자 회원 가입 APi")
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
