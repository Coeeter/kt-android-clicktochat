package com.nasportfolio.clicktochat.data.users.remote

import com.nasportfolio.clicktochat.data.users.remote.dtos.DeleteAccountDto
import com.nasportfolio.clicktochat.data.users.remote.dtos.UpdateAccountDto
import com.nasportfolio.clicktochat.data.users.remote.dtos.UserDto
import com.nasportfolio.clicktochat.data.utils.createAuthorizationHeader
import com.nasportfolio.clicktochat.utils.baseUrl
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import javax.inject.Inject

class KtorUserApi @Inject constructor(
    private val httpClient: HttpClient
) : UserApi {
    private val url = "$baseUrl/api/users"

    override suspend fun getAllUsers(): List<UserDto> {
        return httpClient.get(url).body()
    }

    override suspend fun searchForUsersByUsername(username: String): List<UserDto> {
        return httpClient.get("$url/search") {
            url {
                parameters.append("q", username)
            }
        }.body()
    }

    override suspend fun getUserById(id: String): UserDto {
        return httpClient.get("$url/$id").body()
    }

    override suspend fun updateAccount(
        token: String,
        username: String?,
        email: String?,
        fcmToken: String?
    ): UserDto {
        return httpClient.put(url) {
            createAuthorizationHeader(token)
            contentType(ContentType.Application.Json)
            setBody(
                body = UpdateAccountDto(
                    username = username,
                    email = email,
                    fcmToken = fcmToken
                )
            )
        }.body()
    }

    override suspend fun updateProfileImage(token: String, image: ByteArray): UserDto {
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
            },
        ) {
            createAuthorizationHeader(token)
        }.body()
    }

    override suspend fun deleteAccount(token: String, password: String) {
        httpClient.delete(url) {
            createAuthorizationHeader(token)
            contentType(ContentType.Application.Json)
            setBody(
                body = DeleteAccountDto(
                    password = password
                )
            )
        }
    }

    override suspend fun deleteFcmToken(token: String) {
        httpClient.delete("$url/token") {
            createAuthorizationHeader(token)
        }
    }

    override suspend fun deleteProfileImage(token: String): UserDto {
        return httpClient.delete("$url/images") {
            createAuthorizationHeader(token)
        }.body()
    }

}