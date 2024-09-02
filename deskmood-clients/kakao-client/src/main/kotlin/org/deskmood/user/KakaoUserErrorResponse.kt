package org.deskmood.user

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoUserErrorResponse(
    val code: String,
    @JsonProperty("msg")
    val message: String
)
