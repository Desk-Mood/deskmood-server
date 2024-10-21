package org.deskmood.error

import org.deskmood.http.HttpStatus

interface S3ClientErrorType : ErrorType

data object NotSupportedFileType : S3ClientErrorType {
    override val httpStatusCode = HttpStatus.BAD_REQUEST
    override val errorCode = "FILE_400_0"
    override val message = "지원하지 않는 파일 타입입니다."
    override val logLevel = LogLevel.INFO
}
