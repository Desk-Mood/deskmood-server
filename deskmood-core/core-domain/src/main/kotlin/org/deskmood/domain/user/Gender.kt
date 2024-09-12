package org.deskmood.domain.user

import org.deskmood.domain.base.ValueEnum
import org.deskmood.domain.error.InvalidEnumValue
import org.deskmood.error.DeskmoodException

enum class Gender(
    override val value: String
) : ValueEnum<Gender> {

    MAN("남성"),
    WOMAN("여성");

    companion object {
        fun resolve(value: String): Gender {
            return when (value) {
                "male", "m" -> MAN
                "female", "f" -> WOMAN
                else -> throw DeskmoodException(InvalidEnumValue(entries.map { it.value }))
            }
        }
    }
}
