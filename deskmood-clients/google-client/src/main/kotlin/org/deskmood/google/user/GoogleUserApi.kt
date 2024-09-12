package org.deskmood.google.user

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "GoogleUser",
    url = "https://people.googleapis.com/v1/people/me?personFields=birthdays,genders,emailAddresses,names,photos",
    configuration = [GoogleUserErrorDecoder::class]
)
interface GoogleUserApi {

    @GetMapping
    fun readUserProfile(
        @RequestHeader("Authorization") bearerToken: String
    ): GoogleUserResponse
}
