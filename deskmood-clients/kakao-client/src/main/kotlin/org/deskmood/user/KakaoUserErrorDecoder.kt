package org.deskmood.user

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import org.deskmood.config.KakaoErrorResponse
import org.deskmood.error.DeskmoodException
import org.deskmood.error.ExpiredToken
import org.deskmood.error.Forbidden
import org.deskmood.error.InvalidToken
import org.deskmood.error.KakaoClientError
import org.deskmood.error.NotFound
import org.deskmood.error.Unauthorized

class KakaoUserErrorDecoder(
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
            "unauthorized" -> DeskmoodException(Unauthorized)
            "invalid_token" -> DeskmoodException(InvalidToken)
            "expired_token" -> DeskmoodException(ExpiredToken)
            "forbidden" -> DeskmoodException(Forbidden)
            "insufficient_scope", "access_denied", "data_access_restricted" -> DeskmoodException(Forbidden)
            "not_found" -> DeskmoodException(NotFound)
            else -> DeskmoodException(KakaoClientError)
        }
    }
}
