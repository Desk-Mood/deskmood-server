package org.deskmood.admin.error

import org.deskmood.error.ErrorType
import org.deskmood.error.LogLevel
import org.deskmood.http.HttpStatus

interface AdminApiErrorType : ErrorType

data object AdminAlreadyHasJobData : AdminApiErrorType {
    override val httpStatusCode: HttpStatus = HttpStatus.BAD_REQUEST
    override val errorCode: String = "ADMIN_JOB_400_1"
    override val message: String = "이미 등록된 데이터(직업)가 있습니다."
    override val logLevel: LogLevel = LogLevel.INFO
}
