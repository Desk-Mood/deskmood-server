package org.deskmood.google.auth

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import org.deskmood.error.DeskmoodException
import org.deskmood.google.error.GoogleClientError
import org.deskmood.google.error.InvalidGrant
import org.deskmood.google.error.RedirectUriMismatch

class GoogleAuthErrorDecoder(
    private val objectMapper: ObjectMapper
) : ErrorDecoder {

    override fun decode(
        methodKey: String,
        response: Response
    ): Exception {
        val errorResponse = objectMapper.readValue(
            response.body().asInputStream(),
            GoogleAuthErrorResponse::class.java
        )

        return when (errorResponse.error) {
            "invalid_grant" -> DeskmoodException(InvalidGrant)
            "redirect_uri_mismatch" -> DeskmoodException(RedirectUriMismatch)
            else -> DeskmoodException(GoogleClientError)
        }
    }
}
