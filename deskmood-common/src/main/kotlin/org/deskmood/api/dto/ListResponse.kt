package org.deskmood.api.dto

data class ListResponse<T>(
    val count: Int,
    val list: List<T>
) {

    companion object {
        fun <T, R> of(
            list: List<T>,
            mapAction: (T) -> R
        ): ListResponse<R> {
            return ListResponse(
                list.size,
                list.map { mapAction.invoke(it) }
            )
        }
    }
}
