package org.deskmood.docs.user

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.wwan13.api.document.snippets.BOOLEAN
import io.wwan13.api.document.snippets.DATE
import io.wwan13.api.document.snippets.ENUM
import io.wwan13.api.document.snippets.NUMBER
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.moreAbout
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.controller.user.UserController
import org.deskmood.controller.user.dto.UserAppendRequest
import org.deskmood.docs.ApiDocsTest
import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.user.Gender
import org.deskmood.domain.user.UserService
import org.deskmood.external.oauth.OauthPlatformSelector
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [UserController::class])
class UserApiDocsTest(
    private val userController: UserController
) : ApiDocsTest(userController, "user") {

    @MockkBean
    private lateinit var userService: UserService

    @MockkBean
    private lateinit var oauthPlatformSelector: OauthPlatformSelector

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
            summary("사용자 회원 가입 API")
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

    @Test
    fun `oauth2 유저 기본 프로필 조회 API`() {
        every { oauthPlatformSelector.select(any()) } returns StubOauth2UserProfileReader()

        val api = api.get("/api/v1/user/oauth2/profile") {
            queryParam("platform", "google")
            queryParam("oauth2Code", "4/0AQlEd8wox65EfTuFeyHIaGMLV0lVo4tNAQiSW5Rh-bbrnbZKK9kZUzkPPfk5-yxdAxX2uQ&")
            queryParam("redirectUri", "http://localhost:3000/auth")
        }

        documentFor(api, "oauth2-basic-user-profile") {
            summary(
                "Oauth2 플랫폼(구글/네이버) 에서 제공하는 기본 유저 정보를 조회하는 API"
                    moreAbout "회원가입 전 기본 값을 불러오기 위해 호출하는 API 입니다."
            )
            queryParameters(
                "platform" whichMeans "oauth2 플랫폼",
                "oauth2Code" whichMeans "oauth2 로그인 요청 후 발급받은 code",
                "redirectUri" whichMeans "등록된 redirect uri"
            )
            responseFields(
                "data.email" isTypeOf STRING whichMeans "이메일",
                "data.nickname" isTypeOf STRING whichMeans "닉네임",
                "data.birth" isTypeOf STRING whichMeans "생일",
                "data.gender" isTypeOf STRING whichMeans "성별 (남성/여성)",
            )
        }
    }
}
