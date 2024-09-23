package org.deskmood.auth

import com.fasterxml.jackson.databind.ObjectMapper
import org.deskmood.api.ApiResponse
import org.deskmood.error.Unauthorized
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class UnauthorizedHandler(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        response.contentType = "application/json; charset=UTF-8"
        response.characterEncoding = "UTF-8"

        val responseWriter = response.writer
        val errorResponse = ApiResponse.error(Unauthorized)
        objectMapper.writeValue(responseWriter, errorResponse)
        responseWriter.flush()
    }
}
