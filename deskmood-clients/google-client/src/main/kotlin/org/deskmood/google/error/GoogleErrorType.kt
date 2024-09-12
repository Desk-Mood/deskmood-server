package org.deskmood.google.error

import org.deskmood.error.ErrorType
import org.deskmood.error.LogLevel
import org.deskmood.http.HttpStatus

interface GoogleErrorType : ErrorType

data object GoogleClientError : GoogleErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    override val errorCode: String = "GOOGLE_CLIENT_500"
    override val message: String = "kakao api 요청을 처리하는데 문제가 발생했습니다."
    override val logLevel: LogLevel = LogLevel.ERROR
}

data object InvalidGrant : GoogleErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "GOOGLE_CLIENT_AUTH_400_1"
    override val message: String = "잘못된 authorization code 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object RedirectUriMismatch : GoogleErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "GOOGLE_CLIENT_AUTH_400_3"
    override val message: String = "잘못된 redirect uri 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object InvalidToken : GoogleErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.UNAUTHORIZED
    override val errorCode: String = "GOOGLE_CLIENT_USER_401_2"
    override val message: String = "올바르지 않은(만료된) 인증토큰 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}
