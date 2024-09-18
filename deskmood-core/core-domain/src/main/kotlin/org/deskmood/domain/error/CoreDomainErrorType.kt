package org.deskmood.domain.error

import org.deskmood.error.ErrorType
import org.deskmood.error.LogLevel
import org.deskmood.http.HttpStatus
import kotlin.reflect.KClass

sealed interface CoreDomainErrorType : ErrorType

data class NoSuchData<T : Any, R>(
    val from: KClass<T>,
    val id: R
) : CoreDomainErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "CORE_DOMAIN_400_1"
    override val message: String = "찾으려는 데이터가 없습니다. (${from.simpleName}-$id)"
    override val logLevel: LogLevel = LogLevel.INFO
}

data object AccessFailed : CoreDomainErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.FORBIDDEN
    override val errorCode: String = "CORE_DOMAIN_403_0"
    override val message: String = "접근 권한이 없습니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data class InvalidEnumValue(
    val enumValues: List<String> = listOf()
) : CoreDomainErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "CORE_DOMAIN_400_2"
    override val message: String = "유효하지 않은 enum value 입니다.[${enumValues.joinToString(",")}]"
    override val logLevel: LogLevel = LogLevel.INFO
}

data object InUsedNickname : CoreDomainErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "USER_400_0"
    override val message: String = "이미 사용중인 닉네임 입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data object UserAlreadyRegistered : CoreDomainErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "USER_400_2"
    override val message: String = "이미 등록된 유저입니다."
    override val logLevel: LogLevel = LogLevel.INFO
}
