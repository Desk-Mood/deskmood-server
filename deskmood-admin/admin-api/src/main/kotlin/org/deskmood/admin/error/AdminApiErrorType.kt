package org.deskmood.admin.error

import org.deskmood.error.ErrorType
import org.deskmood.error.LogLevel
import org.deskmood.http.HttpStatus
import kotlin.reflect.KClass

interface AdminApiErrorType : ErrorType

data class AdminNoSuchData<T : Any, R>(
    val from: KClass<T>,
    val id: R
) : AdminApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "ADMIN_API_400_1"
    override val message: String = "찾으려는 데이터가 없습니다. (${from.simpleName}-$id)"
    override val logLevel: LogLevel = LogLevel.INFO
}

data object AdminAccessFailed : AdminApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.FORBIDDEN
    override val errorCode: String = "ADMIN_API_403_0"
    override val message: String = "접근 권한이 없습니다."
    override val logLevel: LogLevel = LogLevel.INFO
}

data class AdminInvalidEnumValue(
    val enumValues: List<String> = listOf()
) : AdminApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "ADMIN_API_400_2"
    override val message: String = "유효하지 않은 enum value 입니다.[${enumValues.joinToString(",")}]"
    override val logLevel: LogLevel = LogLevel.INFO
}

data object AdminAlreadyHasJobData : AdminApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "ADMIN_JOB_400_1"
    override val message: String = "이미 등록된 데이터(직업)가 있습니다."
    override val logLevel: LogLevel = LogLevel.INFO
}
