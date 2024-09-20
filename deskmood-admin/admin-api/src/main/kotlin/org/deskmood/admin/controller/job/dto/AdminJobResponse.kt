package org.deskmood.admin.controller.job.dto

import org.deskmood.admin.domain.job.AdminJob

data class AdminJobResponse(
    val id: Long,
    val value: String,
    val order: Int
) {
    companion object {
        fun from(job: AdminJob): AdminJobResponse {
            return AdminJobResponse(job.id, job.value, job.order)
        }
    }
}
