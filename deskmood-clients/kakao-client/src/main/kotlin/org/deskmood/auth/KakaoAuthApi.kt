package org.deskmood.auth

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "KakaoAuth",
    url = "https://kauth.kakao.com/oauth",
    configuration = [KakaoAuthErrorDecoder::class]
)
interface KakaoAuthApi {

    @PostMapping(
        name = "/token?grant_type=authorization_code",
        headers = ["Content-type=application/x-www-form-urlencoded;charset=utf-8"]
    )
    fun provideToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("redirect_uri") redirectUri: String,
        @RequestParam("code") code: String
    ): KakaoAuthResponse
}
