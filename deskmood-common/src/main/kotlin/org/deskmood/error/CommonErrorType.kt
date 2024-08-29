package org.deskmood.error

import org.deskmood.http.HttpStatus

sealed interface CommonErrorType : ErrorType

data class InvalidRequestField(
    val fieldValue: String
) : CommonErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "REQUEST_FIELD_400"
    override val message: String = "입력하신 $fieldValue 를 확인해 주세요"
    override val logLevel: LogLevel = LogLevel.INFO
}
