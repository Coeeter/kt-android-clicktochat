package com.nasportfolio.clicktochat.data.auth.local

interface AuthPreferences {
    fun getToken(): String?
    fun saveToken(token: String)
    fun deleteToken()

    fun getCurrentUserId(): String?
    fun saveCurrentUserId(userId: String)
    fun deleteCurrentUserId()

    companion object {
        const val TOKEN_KEY = "token"
        const val USER_KEY = "user"
    }
}