package org.deskmood.domain.auth

import org.deskmood.domain.base.ValueEnum

enum class OauthPlatform(
    override val value: String
) : ValueEnum<OauthPlatform> {
    GOOGLE("google"),
    NAVER("naver"),
    LOCAL("local");
}
