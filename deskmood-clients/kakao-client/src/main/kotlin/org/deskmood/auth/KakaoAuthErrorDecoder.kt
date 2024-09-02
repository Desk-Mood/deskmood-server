package org.deskmood.auth

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import org.deskmood.config.KakaoErrorResponse
import org.deskmood.error.DeskmoodException
import org.deskmood.error.InvalidGrant
import org.deskmood.error.InvalidScope
import org.deskmood.error.KakaoClientError
import org.deskmood.error.RedirectUriMismatch
import java.lang.Exception

class KakaoAuthErrorDecoder(
    private val objectMapper: ObjectMapper
) : ErrorDecoder {

    override fun decode(
        methodKey: String,
        response: Response
    ): Exception {
        val errorResponse = objectMapper.readValue(
            response.body().asInputStream(),
            KakaoErrorResponse::class.java
        )

        return when (errorResponse.error) {
            "invalid_grant" -> DeskmoodException(InvalidGrant)
            "invalid_scope" -> DeskmoodException(InvalidScope)
            "redirect_uri_mismatch" -> DeskmoodException(RedirectUriMismatch)
            else -> DeskmoodException(KakaoClientError)
        }
    }
}
