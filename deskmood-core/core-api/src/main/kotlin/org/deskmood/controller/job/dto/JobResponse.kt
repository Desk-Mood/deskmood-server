package org.deskmood.controller.job.dto

import org.deskmood.domain.job.Job

data class JobResponse(
    val id: Long,
    val value: String
) {
    companion object {
        fun from(job: Job): JobResponse {
            return JobResponse(job.id, job.value)
        }
    }
}
