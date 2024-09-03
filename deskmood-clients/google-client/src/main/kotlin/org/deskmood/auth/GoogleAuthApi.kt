package org.deskmood.auth

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "GoogleAuth",
    url = "https://oauth2.googleapis.com/token",
    configuration = [GoogleAuthErrorDecoder::class]
)
interface GoogleAuthApi {

    @PostMapping(
        path = ["?grant_type=authorization_code"],
        headers = ["Content-type=application/x-www-form-urlencoded;charset=utf-8"]
    )
    fun provideToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("redirect_uri") state: String,
        @RequestParam("code") code: String
    ): GoogleAuthResponse
}
