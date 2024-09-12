package org.deskmood.kakao.user

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import org.deskmood.error.DeskmoodException
import org.deskmood.kakao.error.InvalidToken
import org.deskmood.kakao.error.KakaoClientError

class KakaoUserErrorDecoder(
    private val objectMapper: ObjectMapper
) : ErrorDecoder {

    override fun decode(
        methodKey: String,
        response: Response
    ): Exception {
        val errorResponse = objectMapper.readValue(
            response.body().asInputStream(),
            KakaoUserErrorResponse::class.java
        )

        return when (errorResponse.code) {
            "-401" -> DeskmoodException(InvalidToken)
            else -> DeskmoodException(KakaoClientError)
        }
    }
}
