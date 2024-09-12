package org.deskmood.google.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleAuthErrorResponse(
    val error: String,
    @JsonProperty("error_description")
    val errorDescription: String
)
