package org.deskmood.user

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import org.deskmood.error.DeskmoodException
import org.deskmood.error.GoogleClientError
import org.deskmood.error.InvalidToken

class GoogleUserErrorDecoder(
    private val objectMapper: ObjectMapper
) : ErrorDecoder {

    override fun decode(
        methodKey: String,
        response: Response
    ): Exception {
        val errorResponse = objectMapper.readValue(
            response.body().asInputStream(),
            GoogleUserErrorResponse::class.java
        )

        return when (errorResponse.error.statue) {
            "UNAUTHENTICATED" -> DeskmoodException(InvalidToken)
            else -> DeskmoodException(GoogleClientError)
        }
    }
}
