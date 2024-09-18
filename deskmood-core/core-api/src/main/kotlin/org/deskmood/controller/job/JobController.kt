package org.deskmood.controller.job

import org.deskmood.api.ApiResponse
import org.deskmood.api.dto.ListResponse
import org.deskmood.controller.job.dto.JobResponse
import org.deskmood.domain.job.JobService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/job")
class JobController(
    private val jobService: JobService
) {

    @GetMapping
    fun readAll(): ApiResponse<ListResponse<JobResponse>> {
        val jobs = jobService.readAll()
        val response = ListResponse.of(jobs) { JobResponse.from(it) }

        return ApiResponse.success(response)
    }
}
