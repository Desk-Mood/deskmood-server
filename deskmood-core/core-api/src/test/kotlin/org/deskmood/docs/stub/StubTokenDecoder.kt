package org.deskmood.docs.stub

import io.wwan13.wintersecurity.jwt.TokenClaims
import io.wwan13.wintersecurity.jwt.TokenDecoder

class StubTokenDecoder : TokenDecoder {
    override fun decode(token: String?): TokenClaims? {
        return null
    }
}
