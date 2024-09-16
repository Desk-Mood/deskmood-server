package org.deskmood.docs.user

import io.mockk.every
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.hasValues
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.provideMessage
import io.wwan13.api.document.snippets.whichMeans
import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.error.NoSuchData
import org.deskmood.domain.user.User
import org.junit.jupiter.api.Test

class Oauth2BasicUserProfileApiDocsTest : UserApiDocsTest() {

    @Test
    fun `oauth2 유저 기본 프로필 조회 API`() {
        every { oauthPlatformSelector.select(any()) } returns StubOauth2UserProfileReader()

        val api = api.get("/api/v1/user/oauth2/profile") {
            queryParam("platform", "google")
            queryParam("oauth2Code", "4/0AQlEd8wox65EfTuFeyHIaGMLV0lVo4tNAQiSW5Rh-bbrnbZKK9kZUzkPPfk5-yxdAxX2uQ&")
            queryParam("redirectUri", "http://localhost:3000/auth")
        }

        documentFor(api, "oauth2-basic-user-profile") {
            summary("Oauth2 플랫폼(구글/네이버) 에서 제공하는 기본 유저 정보를 조회하는 API")
            guide(
                "회원가입 전 oauth2 플랫폼을 통해 기본 값을 불러오기 위한 API 입니다."
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
