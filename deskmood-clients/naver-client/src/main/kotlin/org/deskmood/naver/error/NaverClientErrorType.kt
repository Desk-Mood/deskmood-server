package org.deskmood.naver.error

import org.deskmood.error.ErrorType
import org.deskmood.error.LogLevel
import org.deskmood.http.HttpStatus

interface NaverClientErrorType : ErrorType

data object NaverClientError : NaverClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    override val errorCode: String = "NAVER_CLIENT_500"
    override val message: String = "naver api 요청을 처리하는데 문제가 발생했습니다."
    override val logLevel: LogLevel = LogLevel.ERROR
}

data object InvalidGrant : NaverClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "NAVER_CLIENT_AUTH_400_1"
    override val message: String = "잘못된 authorization code 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object InvalidToken : NaverClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.UNAUTHORIZED
    override val errorCode: String = "NAVER_CLIENT_USER_401_2"
    override val message: String = "올바르지 않은(만료된) 인증토큰 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}
