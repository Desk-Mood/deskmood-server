package org.deskmood.error

class DeskmoodException(
    val errorType: ErrorType
) : RuntimeException(errorType.message)
