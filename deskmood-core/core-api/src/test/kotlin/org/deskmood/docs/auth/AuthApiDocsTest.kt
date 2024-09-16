package org.deskmood.docs.auth

import com.ninjasquad.springmockk.MockkBean
import org.deskmood.auth.AuthTokenProvider
import org.deskmood.controller.auth.AuthController
import org.deskmood.docs.ApiDocsTest
import org.deskmood.domain.user.UserService
import org.deskmood.external.oauth.OauthPlatformSelector
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [AuthController::class])
abstract class AuthApiDocsTest : ApiDocsTest("auth") {

    @MockkBean
    lateinit var userService: UserService

    @MockkBean
    lateinit var oauthPlatformSelector: OauthPlatformSelector

    @MockkBean
    lateinit var authTokenProvider: AuthTokenProvider
}
