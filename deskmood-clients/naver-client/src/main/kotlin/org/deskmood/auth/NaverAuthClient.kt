package org.deskmood.auth

import org.deskmood.config.NaverProperties
import org.springframework.stereotype.Component

@Component
class NaverAuthClient(
    private val naverAuthApi: NaverAuthApi,
    private val naverAuthErrorHandler: NaverAuthErrorHandler,
    private val naverProperties: NaverProperties
) {

    fun provideAuthToken(
        code: String,
        state: String
    ): NaverAuthToken {
        val response = naverAuthApi.provideToken(
            clientId = naverProperties.clientId,
            clientSecret = naverProperties.clientSecret,
            state = state,
            code = code
        )
        if (response.hasError) {
            naverAuthErrorHandler.handle(response)
        }
        return response.toNaverAuthToken()
    }
}
