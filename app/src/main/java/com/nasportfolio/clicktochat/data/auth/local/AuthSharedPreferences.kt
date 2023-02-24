package com.nasportfolio.clicktochat.data.auth.local

import android.content.SharedPreferences
import javax.inject.Inject

class AuthSharedPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : AuthPreferences {
    override fun getToken(): String? {
        return sharedPreferences.getString(AuthPreferences.TOKEN_KEY, null)
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString(AuthPreferences.TOKEN_KEY, token)
            .apply()
    }

    override fun deleteToken() {
        sharedPreferences.edit()
            .remove(AuthPreferences.TOKEN_KEY)
            .apply()
    }
}