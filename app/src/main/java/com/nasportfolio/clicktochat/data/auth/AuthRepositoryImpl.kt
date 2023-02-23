package com.nasportfolio.clicktochat.data.auth

import com.nasportfolio.clicktochat.domain.auth.AuthRepository
import com.nasportfolio.clicktochat.domain.users.User

class AuthRepositoryImpl : AuthRepository {
    override fun getToken(): String {
        TODO("Not yet implemented")
    }

    override fun saveToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun deleteToken() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserByToken(token: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(token: String, password: String, oldPassword: String) {
        TODO("Not yet implemented")
    }

    override suspend fun login(email: String, password: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
        fcmToken: String
    ): String {
        TODO("Not yet implemented")
    }
}