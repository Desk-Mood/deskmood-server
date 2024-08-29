package org.deskmood.validator.requestvalidator

import org.deskmood.error.DeskmoodException
import org.deskmood.error.ErrorType

object RequestValidator {

    fun validate(
        errorType: ErrorType,
        condition: () -> Boolean
    ) {
        if (!condition.invoke()) {
            throw DeskmoodException(errorType)
        }
    }

    fun validate(
        errorCode: String,
        errorMessage: String,
        condition: () -> Boolean
    ) {
        if (!condition.invoke()) {
            val errorType = InvalidInputValue(errorCode, errorMessage)
            throw DeskmoodException(errorType)
        }
    }

    fun validate(condition: () -> Boolean) {
        if (!condition.invoke()) {
            throw DeskmoodException(DefaultInvalidInputValue)
        }
    }
}
