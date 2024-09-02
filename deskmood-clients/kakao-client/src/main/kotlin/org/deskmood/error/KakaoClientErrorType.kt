package org.deskmood.error

import org.deskmood.http.HttpStatus

sealed interface KakaoClientErrorType : ErrorType

data object KakaoClientError : KakaoClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    override val errorCode: String = "KAKAO_CLIENT_500"
    override val message: String = "kakao api 요청을 처리하는데 문제가 발생했습니다."
    override val logLevel: LogLevel = LogLevel.ERROR
}

data object InvalidGrant : KakaoClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "KAKAO_CLIENT_AUTH_400_1"
    override val message: String = "잘못된 authorization code 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object InvalidScope : KakaoClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "KAKAO_CLIENT_AUTH_400_2"
    override val message: String = "허용되지 않은 scope 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object RedirectUriMismatch : KakaoClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "KAKAO_CLIENT_AUTH_400_3"
    override val message: String = "잘못된 redirect uri 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object Unauthorized : KakaoClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.UNAUTHORIZED
    override val errorCode: String = "KAKAO_CLIENT_USER_401_1"
    override val message: String = "올바른 인증 정보가 없습니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object Forbidden : KakaoClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.FORBIDDEN
    override val errorCode: String = "KAKAO_CLIENT_USER_403_1"
    override val message: String = "접근 권한이 없습니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object InvalidToken : KakaoClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.UNAUTHORIZED
    override val errorCode: String = "KAKAO_CLIENT_USER_401_2"
    override val message: String = "올바르지 않은 인증토큰 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object ExpiredToken : KakaoClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "KAKAO_CLIENT_USER_400_1"
    override val message: String = "만료된 인증토큰 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object NotFound : KakaoClientErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.NOT_FOUND
    override val errorCode: String = "KAKAO_CLIENT_USER_404"
    override val message: String = "만료된 인증토큰 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}
