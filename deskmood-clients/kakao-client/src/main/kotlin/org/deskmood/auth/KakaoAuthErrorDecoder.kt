package org.deskmood.auth

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import org.deskmood.error.DeskmoodException
import org.deskmood.error.InvalidGrant
import org.deskmood.error.KakaoClientError
import org.deskmood.error.RedirectUriMismatch

class KakaoAuthErrorDecoder(
    private val objectMapper: ObjectMapper
) : ErrorDecoder {

    override fun decode(
        methodKey: String,
        response: Response
    ): Exception {
        val errorResponse = objectMapper.readValue(
            response.body().asInputStream(),
            KakaoAuthErrorResponse::class.java
        )

        return when (errorResponse.error) {
            "KOE204" -> DeskmoodException(InvalidGrant)
            "KOE006" -> DeskmoodException(RedirectUriMismatch)
            else -> DeskmoodException(KakaoClientError)
        }
    }
}
