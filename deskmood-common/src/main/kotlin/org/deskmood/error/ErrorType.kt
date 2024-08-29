package org.deskmood.error

import org.deskmood.http.HttpStatus

interface ErrorType {
    val httpStatusCode: HttpStatus
    val errorCode: String
    val message: String
    val logLevel: LogLevel
}
