package org.deskmood.error

import org.deskmood.http.HttpStatus

sealed interface CoreApiErrorType : ErrorType

data object Unauthorized : CoreApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.UNAUTHORIZED
    override val errorCode: String = "CORE_API_401"
    override val message: String = "유효한 인증 정보가 없습니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object Forbidden : CoreApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.FORBIDDEN
    override val errorCode: String = "CORE_API_403"
    override val message: String = "접근 권한이 없습니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object InvalidJwtToken : CoreApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.UNAUTHORIZED
    override val errorCode: String = "CORE_API_401_1"
    override val message: String = "잟못된 인증토큰 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object ExpiredJwtToken : CoreApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.UNAUTHORIZED
    override val errorCode: String = "CORE_API_401_2"
    override val message: String = "만료 인증토큰 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object InvalidNickname : CoreApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "USER_400_1"
    override val message: String = "유효하지 않은 닉네임 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}
