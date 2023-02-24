package com.nasportfolio.clicktochat.data.auth.local

interface AuthPreferences {
    fun getToken(): String?
    fun saveToken(token: String)
    fun deleteToken()

    companion object {
        const val TOKEN_KEY = "token"
    }
}