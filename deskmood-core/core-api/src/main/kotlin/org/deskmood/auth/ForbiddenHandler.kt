package org.deskmood.auth

import com.fasterxml.jackson.databind.ObjectMapper
import org.deskmood.api.ApiResponse
import org.deskmood.error.Forbidden
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ForbiddenHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.contentType = "application/json; charset=UTF-8"
        response.characterEncoding = "UTF-8"

        val responseWriter = response.writer
        val errorResponse = ApiResponse.error(Forbidden)
        objectMapper.writeValue(responseWriter, errorResponse)
        responseWriter.flush()
    }
}
