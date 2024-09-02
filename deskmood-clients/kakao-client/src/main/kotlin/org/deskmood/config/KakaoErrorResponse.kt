package org.deskmood.config

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoErrorResponse(
    val error: String,
    @JsonProperty("error_description")
    val errorDescription: String
)
