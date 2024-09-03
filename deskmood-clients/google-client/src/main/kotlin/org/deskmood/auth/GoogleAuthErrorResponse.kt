package org.deskmood.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleAuthErrorResponse(
    val error: String,
    @JsonProperty("error_description")
    val errorDescription: String
)
