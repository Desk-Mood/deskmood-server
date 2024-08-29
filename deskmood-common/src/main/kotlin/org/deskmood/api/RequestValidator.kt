package org.deskmood.api

import org.deskmood.error.DeskmoodException
import org.deskmood.error.InvalidRequestField

object RequestValidator {

    fun <T> validate(
        field: T,
        condition: (T) -> Boolean
    ) {
        if (!condition.invoke(field)) {
            throw DeskmoodException(InvalidRequestField(field.toString()))
        }
    }
}
