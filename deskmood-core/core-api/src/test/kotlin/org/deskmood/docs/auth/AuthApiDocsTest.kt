package org.deskmood.docs.auth

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.moreAbout
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.auth.AuthToken
import org.deskmood.auth.AuthTokenProvider
import org.deskmood.controller.auth.AuthController
import org.deskmood.controller.auth.dto.LoginRequest
import org.deskmood.docs.ApiDocsTest
import org.deskmood.docs.user.StubOauth2UserProfileReader
import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.base.Timestamp
import org.deskmood.domain.user.Gender
import org.deskmood.domain.user.User
import org.deskmood.domain.user.UserProfile
import org.deskmood.domain.user.UserRole
import org.deskmood.domain.user.UserService
import org.deskmood.external.oauth.OauthPlatformSelector
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import java.time.LocalDate

@WebMvcTest(controllers = [AuthController::class])
class AuthApiDocsTest(
    private val authController: AuthController
) : ApiDocsTest(authController, "auth") {

    @MockkBean
    private lateinit var userService: UserService

    @MockkBean
    private lateinit var oauthPlatformSelector: OauthPlatformSelector

    @MockkBean
    private lateinit var authTokenProvider: AuthTokenProvider

    @Test
    fun `deskmood 인증 토큰 발급 API`() {
        every { oauthPlatformSelector.select(any()) } returns StubOauth2UserProfileReader()
        every { userService.readByOauth(any()) } returns User(
            1L, Timestamp(), UserRole.ROLE_USER, Oauth(OauthPlatform.GOOGLE, "ktw01023@gmail.com"),
            UserProfile("kim", LocalDate.of(2001, 2, 22), Gender.MAN, "개발자")
        )
        every { authTokenProvider.provide(any()) } returns AuthToken("access token", "refresh token")

        val api = api.post("/api/v1/auth/login") {
            queryParam("platform", "google")
            requestBody(
                LoginRequest(
                    "4/0AQlEd8wox65EfTuFeyHIaGMLV0lVo4tNAQiSW5Rh-bbrnbZKK9kZUzkPPfk5-yxdAxX2uQ&",
                    "http://localhost:3000/auth"
                )
            )
        }

        documentFor(api, "provide-deskmood-auth-token") {
            summary(
                "Oauth2 플랫폼(구글/네이버) 에서 제공하는 기본 유저 정보를 조회하는 API"
                    moreAbout "회원가입 전 기본 값을 불러오기 위해 호출하는 API 입니다."
            )
            queryParameters(
                "platform" whichMeans "oauth2 플랫폼",
            )
            requestFields(
                "oauth2Code" isTypeOf STRING whichMeans "oauth2 로그인 요청 후 발급받은 code",
                "redirectUri" isTypeOf STRING whichMeans "등록된 redirect uri"
            )
            responseFields(
                "data.accessToken" isTypeOf STRING whichMeans "접근 토큰",
                "data.refreshToken" isTypeOf STRING whichMeans "재발급 토큰",
            )
        }
    }
}
