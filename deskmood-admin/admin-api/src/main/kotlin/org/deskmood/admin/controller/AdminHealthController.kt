package org.deskmood.admin.controller

import org.deskmood.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/health")
class AdminHealthController {

    @GetMapping
    fun healthChecking(): ApiResponse<Any> {
        return ApiResponse.success()
    }
}
