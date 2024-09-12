package org.deskmood.naver.auth

import org.deskmood.error.DeskmoodException
import org.deskmood.naver.error.InvalidGrant
import org.deskmood.naver.error.NaverClientError
import org.springframework.stereotype.Component

@Component
class NaverAuthErrorHandler {

    fun handle(response: NaverAuthResponse) {
        when (response.error) {
            "invalid_request", "024" -> throw DeskmoodException(InvalidGrant)
            else -> throw DeskmoodException(NaverClientError)
        }
    }
}
