package org.deskmood.naver.auth

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "NaverAuth",
    url = "https://nid.naver.com/oauth2.0/token"
)
interface NaverAuthApi {

    @PostMapping("?grant_type=authorization_code")
    fun provideToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("state") state: String,
        @RequestParam("code") code: String
    ): NaverAuthResponse
}
