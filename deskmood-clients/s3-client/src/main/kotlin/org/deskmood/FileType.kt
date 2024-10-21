package org.deskmood

import org.deskmood.error.DeskmoodException
import org.deskmood.error.NotSupportedFileType

enum class FileType(
    val value: String
) {
    IMAGE("image"),
    HTML("html"),
    MARKDOWN("markdown");

    companion object {
        fun resolve(value: String): FileType {
            return entries
                .firstOrNull { it.value == value }
                ?: throw DeskmoodException(NotSupportedFileType)
        }
    }
}
