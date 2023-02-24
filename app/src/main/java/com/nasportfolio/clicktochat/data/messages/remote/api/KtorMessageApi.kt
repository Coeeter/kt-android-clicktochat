package com.nasportfolio.clicktochat.data.messages.remote.api

import com.nasportfolio.clicktochat.data.messages.remote.dtos.MessageDto
import com.nasportfolio.clicktochat.data.messages.remote.dtos.UrlDto
import com.nasportfolio.clicktochat.data.utils.createAuthorizationHeader
import com.nasportfolio.clicktochat.utils.baseUrl
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import javax.inject.Inject

class KtorMessageApi @Inject constructor(
    private val httpClient: HttpClient
) : MessageApi {
    private val url = "$baseUrl/api/messages"

    override suspend fun getAllMessagesOfChat(token: String, otherUserId: String): List<MessageDto> {
        return httpClient.get("$url/$otherUserId") {
            createAuthorizationHeader(token)
        }.body()
    }

    override suspend fun getAllMessagesOfUser(token: String): List<MessageDto> {
        return httpClient.get(url) {
            createAuthorizationHeader(token)
        }.body()
    }

    override suspend fun uploadImage(token: String, image: ByteArray): String {
        return httpClient.submitFormWithBinaryData(
            url = "$url/images",
            formData = formData {
                append(
                    key = "image",
                    value = image,
                    headers = Headers.build {
                        append(HttpHeaders.ContentType, ContentType.Image.Any)
                    }
                )
            }
        ) {
            createAuthorizationHeader(token)
        }.body<UrlDto>().url
    }
}