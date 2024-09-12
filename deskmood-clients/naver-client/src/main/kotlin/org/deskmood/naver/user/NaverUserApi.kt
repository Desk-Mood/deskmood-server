package org.deskmood.naver.user

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "NaverUser",
    url = "https://openapi.naver.com/v1/nid/me",
    configuration = [NaverUserErrorDecoder::class]
)
interface NaverUserApi {

    @GetMapping
    fun readUserProfile(
        @RequestHeader("Authorization") bearerToken: String,
    ): NaverUserResponse
}
