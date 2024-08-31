package org.deskmood.controller

import mu.KotlinLogging
import org.deskmood.api.ApiResponse
import org.deskmood.api.dto.ErrorResponse
import org.deskmood.error.DeskmoodException
import org.deskmood.error.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class ApiControllerAdvice {

    @ExceptionHandler(DeskmoodException::class)
    fun handleDeskmoodException(
        e: DeskmoodException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        val errorType = e.errorType

        when (errorType.logLevel) {
            LogLevel.TRACE -> log.trace(e.message)
            LogLevel.DEBUG -> log.debug(e.message)
            LogLevel.INFO -> log.info(e.message)
            LogLevel.WARN -> log.warn(e.message)
            LogLevel.ERROR -> log.error(e.stackTraceToString())
        }

        val response = ApiResponse.error(errorType)
        return ResponseEntity.status(errorType.httpStatusCode.value).body(response)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(
        e: HttpRequestMethodNotSupportedException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_405", "지원하지 않는 메소드 입니다.")
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleHttpRequestMethodNotSupportedException(
        e: NoHandlerFoundException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_404", "존재하지 않는 API 요청 입니다.")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(
        e: MissingServletRequestParameterException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.info(e.message)
        val response = ApiResponse.error("HTTP_400", "요청에 필요한 쿼리 파라미터 ${e.parameterName}가 없습니다.")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
        e: HttpMessageNotReadableException
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        if (e.rootCause is DeskmoodException) {
            val exception = e.rootCause as DeskmoodException
            log.info(exception.message)
            val response = ApiResponse.error(exception.errorType)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
        }

        log.info(e.message)
        val response = ApiResponse.error("HTTP_400", "잘못된 요청 메세지 입니다.")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
        e: Exception
    ): ResponseEntity<ApiResponse<ErrorResponse>> {
        log.error(e.stackTraceToString())
        val response = ApiResponse.error("HTTP_500", "서버에서 알 수 없는 에러가 발생하였습니다.")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}
