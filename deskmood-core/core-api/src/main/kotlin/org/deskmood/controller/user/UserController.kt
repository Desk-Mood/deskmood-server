package org.deskmood.controller.user

import org.deskmood.api.ApiResponse
import org.deskmood.api.dto.BooleanResultResponse
import org.deskmood.domain.user.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/nickname/available")
    fun isAvailableNickname(
        @RequestParam("nickname") nickname: String
    ): ApiResponse<BooleanResultResponse> {
        val result = userService.isAvailableNickname(nickname)
        val response = BooleanResultResponse(result)

        return ApiResponse.success(response)
    }
}
