package org.deskmood.controller.user

import org.deskmood.api.ApiResponse
import org.deskmood.api.dto.BooleanResultResponse
import org.deskmood.api.dto.IdResponse
import org.deskmood.controller.user.dto.OauthUserProfileResponse
import org.deskmood.controller.user.dto.UserAppendRequest
import org.deskmood.domain.user.UserService
import org.deskmood.external.oauth.OauthPlatformSelector
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService,
    private val oauthPlatformSelector: OauthPlatformSelector
) {

    @PostMapping
    fun appendUser(
        @RequestBody request: UserAppendRequest
    ): ApiResponse<IdResponse> {
        val userId = userService.appendUser(
            request.toOauth(),
            request.toUserProfile()
        )
        val response = IdResponse(userId)

        return ApiResponse.success(response)
    }

    @GetMapping("/nickname/available")
    fun isAvailableNickname(
        @RequestParam("nickname") nickname: String
    ): ApiResponse<BooleanResultResponse> {
        val result = userService.isAvailableNickname(nickname)
        val response = BooleanResultResponse(result)

        return ApiResponse.success(response)
    }

    @GetMapping("/oauth2/profile")
    fun readOauth2UserProfile(
        @RequestParam("platform") platform: String,
        @RequestParam("oauth2Code") code: String,
        @RequestParam("redirectUri") redirectUri: String
    ): ApiResponse<OauthUserProfileResponse> {
        val oauth2UserProfileReader = oauthPlatformSelector.select(platform)
        val profile = oauth2UserProfileReader.read(code, redirectUri)
        val response = OauthUserProfileResponse.from(profile)

        return ApiResponse.success(response)
    }
}
