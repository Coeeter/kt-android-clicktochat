package com.nasportfolio.clicktochat.data.auth

import com.nasportfolio.clicktochat.data.auth.local.AuthPreferences
import com.nasportfolio.clicktochat.data.auth.remote.AuthApi
import com.nasportfolio.clicktochat.domain.auth.AuthRepository
import com.nasportfolio.clicktochat.domain.users.User
import com.nasportfolio.clicktochat.domain.utils.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authPreferences: AuthPreferences,
    private val authApi: AuthApi
) : AuthRepository {
    override fun getToken(): Resource<String> {
        return try {
            val token = authPreferences.getToken()!!
            Resource.Success(token)
        } catch (e: NullPointerException) {
            Resource.Failure(Exception("Not logged in"))
        }
    }

    override fun saveToken(token: String) {
        authPreferences.saveToken(token)
    }

    override fun deleteToken() {
        authPreferences.deleteToken()
    }

    override fun getUserId(): Resource<String> {
        return try {
            val token = authPreferences.getCurrentUserId()!!
            Resource.Success(token)
        } catch (e: NullPointerException) {
            Resource.Failure(Exception("Not logged in"))
        }
    }

    override fun saveUserId(userId: String) {
        authPreferences.saveCurrentUserId(userId = userId)
    }

    override fun deleteUserId() {
        authPreferences.deleteCurrentUserId()
    }

    override suspend fun getUserByToken(token: String): Resource<User> {
        return try {
            val user = authApi.getUserByToken(token)
            Resource.Success(user.toExternalUser())
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun updatePassword(token: String, password: String, oldPassword: String) {
        authApi.updatePassword(token, password, oldPassword)
    }

    override suspend fun login(email: String, password: String): Resource<String> {
        return try {
            val token = authApi.login(email, password)
            Resource.Success(token)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
        fcmToken: String
    ): Resource<String> {
        return try {
            val token = authApi.register(username, email, password, fcmToken)
            Resource.Success(token)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}