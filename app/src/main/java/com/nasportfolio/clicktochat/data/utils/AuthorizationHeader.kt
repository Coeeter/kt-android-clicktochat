package com.nasportfolio.clicktochat.data.utils

import io.ktor.client.request.*
import io.ktor.http.*

fun HttpMessageBuilder.createAuthorizationHeader(
    token: String,
    block: HeadersBuilder.() -> Unit = {}
) = headers {
    val bearerToken = "Bearer $token"
    append(HttpHeaders.Authorization, bearerToken)
    apply(block)
}