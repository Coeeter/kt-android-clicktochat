package com.nasportfolio.clicktochat.domain.auth

import com.nasportfolio.clicktochat.domain.users.User
import com.nasportfolio.clicktochat.domain.utils.Resource

interface AuthRepository {
    fun getToken(): Resource<String>
    fun saveToken(token: String)
    fun deleteToken()
    suspend fun getUserByToken(token: String): Resource<User>
    suspend fun updatePassword(token: String, password: String, oldPassword: String)
    suspend fun login(email: String, password: String): Resource<String>
    suspend fun register(
        username: String,
        email: String,
        password: String,
        fcmToken: String
    ): Resource<String>
}