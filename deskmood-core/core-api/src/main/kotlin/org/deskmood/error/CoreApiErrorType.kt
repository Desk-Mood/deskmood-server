package org.deskmood.error

import org.deskmood.http.HttpStatus

sealed interface CoreApiErrorType : ErrorType

data object InvalidNickname : CoreApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "USER_400_1"
    override val message: String = "유효하지 않은 닉네임 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}
