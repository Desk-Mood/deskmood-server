package org.deskmood.user

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleUserErrorResponse(
    val error: String,
    @JsonProperty("error_description")
    val errorDescription: String
)
