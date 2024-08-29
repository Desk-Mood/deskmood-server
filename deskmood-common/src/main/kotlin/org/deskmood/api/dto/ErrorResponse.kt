package org.deskmood.api.dto

data class ErrorResponse(
    val errorCode: String,
    val message: String,
)
