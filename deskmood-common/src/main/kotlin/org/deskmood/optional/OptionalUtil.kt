package org.deskmood.optional

import java.util.Optional

fun <T, R> Optional<T>.mapOrNull(transform: (T) -> R): R? {
    return this.map { transform.invoke(it) }.orElse(null)
}