package org.deskmood.controller

import org.deskmood.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/health")
class HealthController {

    @GetMapping
    fun healthChecking(): ApiResponse<Any> {
        return ApiResponse.success()
    }
}
