package com.nasportfolio.clicktochat.data.auth.remote

import com.nasportfolio.clicktochat.data.users.remote.dtos.UserDto

interface AuthApi {
    suspend fun getUserByToken(token: String): UserDto
    suspend fun updatePassword(token: String, password: String, oldPassword: String)
    suspend fun login(email: String, password: String): String
    suspend fun register(
        username: String,
        email: String,
        password: String,
        fcmToken: String
    ): String
}