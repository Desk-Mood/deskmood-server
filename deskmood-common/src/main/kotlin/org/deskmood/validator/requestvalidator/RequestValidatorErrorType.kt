package org.deskmood.validator.requestvalidator

import org.deskmood.error.ErrorType
import org.deskmood.error.LogLevel
import org.deskmood.http.HttpStatus

sealed interface RequestValidatorErrorType : ErrorType

data class InvalidInputValue(
    override val errorCode: String,
    override val message: String,
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST,
    override val logLevel: LogLevel = LogLevel.INFO
) : RequestValidatorErrorType

data object DefaultInvalidInputValue : RequestValidatorErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "HTTP_400_0"
    override val message: String = "올바르지 않은 입력값 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}
