package org.deskmood.docs.user

import com.ninjasquad.springmockk.MockkBean
import org.deskmood.controller.user.UserController
import org.deskmood.docs.ApiDocsTest
import org.deskmood.domain.user.UserService
import org.deskmood.external.oauth.OauthPlatformSelector
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [UserController::class])
abstract class UserApiDocsTest : ApiDocsTest("user") {

    @MockkBean
    lateinit var userService: UserService

    @MockkBean
    lateinit var oauthPlatformSelector: OauthPlatformSelector
}
