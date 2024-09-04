package org.deskmood.domain.base

import org.deskmood.domain.error.InvalidEnumValue
import org.deskmood.error.DeskmoodException

interface ValueEnum<T : Enum<T>> {
    val value: String

    companion object {
        inline fun <reified T : Enum<T>> resolve(value: String): T {
            val entries = enumValues<T>()
            return enumValues<T>()
                .singleOrNull { (it as ValueEnum<*>).value == value }
                ?: throw DeskmoodException(InvalidEnumValue(entries.map { (it as ValueEnum<*>).value }))
        }
    }
}
