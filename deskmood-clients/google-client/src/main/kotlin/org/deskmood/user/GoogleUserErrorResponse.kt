package org.deskmood.user

data class GoogleUserErrorResponse(
    val error: GoogleUserError
)

data class GoogleUserError(
    val code: String,
    val message: String,
    val statue: String
)
