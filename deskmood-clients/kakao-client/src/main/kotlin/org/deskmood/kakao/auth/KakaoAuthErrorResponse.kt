package org.deskmood.kakao.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoAuthErrorResponse(
    val error: String,
    @JsonProperty("error_description")
    val errorDescription: String
)
