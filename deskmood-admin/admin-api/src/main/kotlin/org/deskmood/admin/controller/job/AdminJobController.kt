package org.deskmood.admin.controller.job

import org.deskmood.admin.controller.job.dto.AdminJobResponse
import org.deskmood.admin.domain.job.AdminJobService
import org.deskmood.api.ApiResponse
import org.deskmood.api.dto.ListResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/v1/job")
class AdminJobController(
    private val jobService: AdminJobService
) {

    @PostMapping("/setup")
    fun setup(): ApiResponse<Any> {
        jobService.setup()

        return ApiResponse.success()
    }

    @GetMapping
    fun readAll(): ApiResponse<ListResponse<AdminJobResponse>> {
        val jobs = jobService.readAll()
        val response = ListResponse.of(jobs) { AdminJobResponse.from(it) }

        return ApiResponse.success(response)
    }

    @PatchMapping("/{jobId}/relocation/{to}")
    fun relocationOrder(
        @PathVariable jobId: Long,
        @PathVariable to: Int
    ): ApiResponse<Any> {
        jobService.relocationOrder(jobId, to)

        return ApiResponse.success()
    }

    @DeleteMapping
    fun refreshDefault(): ApiResponse<Any> {
        jobService.removeAll()

        return ApiResponse.success()
    }
}
