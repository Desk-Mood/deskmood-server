package org.deskmood.controller

import org.deskmood.api.ApiResponse
import org.deskmood.auth.GoogleAuthClient
import org.deskmood.auth.GoogleAuthToken
import org.deskmood.user.GoogleUserClient
import org.deskmood.user.GoogleUserProfile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    private val googleAuthClient: GoogleAuthClient,
    private val googleUserClient: GoogleUserClient
) {

    @GetMapping("/1")
    fun test1(
        @RequestParam("code") code: String
    ): ApiResponse<GoogleAuthToken> {
        val token = googleAuthClient.provideAuthToken(code, "http://localhost:3000/auth")
        // val result = googleUserClient.readUserProfile(token.accessToken)
        return ApiResponse.success(token)
    }

    @GetMapping("/2")
    fun test2(
        @RequestParam("code") code: String
    ): ApiResponse<GoogleUserProfile> {
        val token = googleAuthClient.provideAuthToken(code, "http://localhost:3000/auth")
        val result = googleUserClient.readUserProfile(token.accessToken)
        return ApiResponse.success(result)
    }
}