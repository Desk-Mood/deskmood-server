package org.deskmood.kakao.user

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "KakaoUser",
    url = "https://kapi.kakao.com/v2/user",
    configuration = [KakaoUserErrorDecoder::class]
)
interface KakaoUserApi {

    @GetMapping(
        path = ["/me?property_keys=[\"kakao_account.profile\"]"],
        headers = ["Content-type=application/x-www-form-urlencoded;charset=utf-8"]
    )
    fun readUserProfile(
        @RequestHeader("Authorization") bearerToken: String,
        @RequestParam("secure_resource") secureResource: Boolean
    ): KakaoUserProfileResponse
}
