package org.deskmood.docs.auth

import io.mockk.every
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.hasValues
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.provideMessage
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.auth.AuthToken
import org.deskmood.controller.auth.dto.LoginRequest
import org.deskmood.docs.user.StubOauth2UserProfileReader
import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.base.Timestamp
import org.deskmood.domain.error.NoSuchData
import org.deskmood.domain.user.Gender
import org.deskmood.domain.user.User
import org.deskmood.domain.user.UserProfile
import org.deskmood.domain.user.UserRole
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ProvideAuthTokenApiDocsTest : AuthApiDocsTest() {

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
            summary("인증 토큰 발급 API")
            guide(
                "deskmood 서비스에서 사용하는 인증 토큰 발급을 위한 API 입니다.",
                "이 API 를 통해 발급받은 access token 을 다른 API 의 헤더에 포함해 주세요."
            )
            containedEnums(
                "platform" hasValues OauthPlatform.entries.map { it.value }
            )
            expectedErrors(
                org.deskmood.google.error.InvalidGrant.errorCode
                    provideMessage org.deskmood.google.error.InvalidGrant.message,
                org.deskmood.google.error.RedirectUriMismatch.errorCode
                    provideMessage org.deskmood.google.error.RedirectUriMismatch.message,
                org.deskmood.naver.error.InvalidGrant.errorCode
                    provideMessage org.deskmood.naver.error.InvalidGrant.message,
                NoSuchData(User::class, 1L).errorCode provideMessage NoSuchData(User::class, 1L).message
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
