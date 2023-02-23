package com.nasportfolio.clicktochat.domain.auth

import com.nasportfolio.clicktochat.domain.users.User

interface AuthRepository {
    fun getToken(): String
    fun saveToken(token: String)
    fun deleteToken()
    suspend fun getUserByToken(token: String): User
    suspend fun updatePassword(password: String, oldPassword: String)
    suspend fun login(email: String, password: String): String
    suspend fun register(
        username: String,
        email: String,
        password: String,
        fcmToken: String
    ): String
}