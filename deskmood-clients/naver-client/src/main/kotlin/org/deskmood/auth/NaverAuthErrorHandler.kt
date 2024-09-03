package org.deskmood.auth

import org.deskmood.error.DeskmoodException
import org.deskmood.error.InvalidGrant
import org.deskmood.error.NaverClientError
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
