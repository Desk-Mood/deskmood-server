package org.deskmood.naver.user

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Response
import feign.codec.ErrorDecoder
import org.deskmood.error.DeskmoodException
import org.deskmood.naver.error.InvalidToken
import org.deskmood.naver.error.NaverClientError

class NaverUserErrorDecoder(
    private val objectMapper: ObjectMapper
) : ErrorDecoder {

    override fun decode(
        methodKey: String,
        response: Response
    ): Exception {
        val errorResponse = objectMapper.readValue(
            response.body().asInputStream(),
            NaverUserErrorResponse::class.java
        )

        return when (errorResponse.resultCode) {
            "024" -> DeskmoodException(InvalidToken)
            else -> DeskmoodException(NaverClientError)
        }
    }
}
