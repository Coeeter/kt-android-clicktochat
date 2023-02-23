package com.nasportfolio.clicktochat.data.auth.remote

import com.nasportfolio.clicktochat.data.auth.remote.dtos.LoginDto
import com.nasportfolio.clicktochat.data.auth.remote.dtos.RegisterDto
import com.nasportfolio.clicktochat.data.auth.remote.dtos.TokenDto
import com.nasportfolio.clicktochat.data.auth.remote.dtos.UpdatePasswordDto
import com.nasportfolio.clicktochat.data.users.remote.dtos.UserDto
import com.nasportfolio.clicktochat.data.utils.createAuthorizationHeader
import com.nasportfolio.clicktochat.utils.baseUrl
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class KtorAuthApi @Inject constructor(
    private val httpClient: HttpClient,
) : AuthApi {
    private val url = "$baseUrl/api/users"

    override suspend fun getUserByToken(token: String): UserDto {
        return httpClient.get("$url/validate-token") {
            createAuthorizationHeader(token)
        }.body()
    }

    override suspend fun updatePassword(token: String, password: String, oldPassword: String) {
        httpClient.put("$url/password") {
            createAuthorizationHeader(token)
            contentType(ContentType.Application.Json)
            setBody(
                body = UpdatePasswordDto(
                    password = password,
                    oldPassword = oldPassword
                )
            )
        }
    }

    override suspend fun login(email: String, password: String): String {
        return httpClient.post("$url/login") {
            contentType(ContentType.Application.Json)
            setBody(
                body = LoginDto(
                    email = email,
                    password = password
                )
            )
        }.body<TokenDto>().token
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
        fcmToken: String
    ): String {
        return httpClient.post("$url/register") {
            contentType(ContentType.Application.Json)
            setBody(
                body = RegisterDto(
                    username = username,
                    email = email,
                    password = password,
                    fcmToken = fcmToken
                )
            )
        }.body<TokenDto>().token
    }
}