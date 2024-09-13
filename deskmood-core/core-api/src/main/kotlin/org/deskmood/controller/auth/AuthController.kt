package org.deskmood.controller.auth

import org.deskmood.api.ApiResponse
import org.deskmood.auth.AuthTokenProvider
import org.deskmood.auth.TokenPayload
import org.deskmood.controller.auth.dto.LoginRequest
import org.deskmood.controller.auth.dto.LoginResponse
import org.deskmood.domain.auth.Oauth
import org.deskmood.domain.auth.OauthPlatform
import org.deskmood.domain.base.ValueEnum
import org.deskmood.domain.user.UserService
import org.deskmood.external.oauth.OauthPlatformSelector
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val oauthPlatformSelector: OauthPlatformSelector,
    private val authTokenProvider: AuthTokenProvider,
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(
        @RequestParam("platform") platform: String,
        @RequestBody request: LoginRequest
    ): ApiResponse<LoginResponse> {
        val oauthUserProfileReader = oauthPlatformSelector.select(platform)
        val profile =
            oauthUserProfileReader.read(request.oauth2Code, request.redirectUri)

        val oauth = Oauth(
            ValueEnum.resolve<OauthPlatform>(platform),
            profile.email
        )
        val user = userService.readByOauth(oauth)

        val tokenPayload = TokenPayload(user.id, user.role)
        val authToken = authTokenProvider.provide(tokenPayload)
        val response = LoginResponse.from(authToken)

        return ApiResponse.success(response)
    }
}
