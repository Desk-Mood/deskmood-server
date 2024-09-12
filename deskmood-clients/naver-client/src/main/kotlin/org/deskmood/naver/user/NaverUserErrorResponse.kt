package org.deskmood.naver.user

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverUserErrorResponse(
    @JsonProperty("resultcode")
    val resultCode: String,
    val message: String
)
