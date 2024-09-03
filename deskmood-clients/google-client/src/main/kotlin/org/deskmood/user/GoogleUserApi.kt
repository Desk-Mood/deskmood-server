package org.deskmood.user

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "GoogleUser",
    url = "https://www.googleapis.com/oauth2/v3/userinfo",
    configuration = [GoogleUserErrorDecoder::class]
)
interface GoogleUserApi {

    @GetMapping
    fun readUserProfile(
        @RequestHeader("Authorization") bearerToken: String
    ): GoogleUserResponse
}
