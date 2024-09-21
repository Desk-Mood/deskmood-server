package org.deskmood.admin.domain.base

import org.deskmood.admin.error.AdminInvalidEnumValue
import org.deskmood.error.DeskmoodException

interface AdminValueEnum<T : Enum<T>> {
    val value: String

    companion object {
        inline fun <reified T : Enum<T>> resolve(value: String): T {
            val entries = enumValues<T>()
            return enumValues<T>()
                .singleOrNull { (it as AdminValueEnum<*>).value == value }
                ?: throw DeskmoodException(AdminInvalidEnumValue(entries.map { (it as AdminValueEnum<*>).value }))
        }
    }
}
