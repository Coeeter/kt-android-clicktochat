package com.nasportfolio.clicktochat.data.users.remote

import com.nasportfolio.clicktochat.data.users.remote.dtos.UserDto

interface UserApi {
    suspend fun getAllUsers(): List<UserDto>
    suspend fun searchForUsersByUsername(username: String): List<UserDto>
    suspend fun getUserById(id: String): UserDto
    suspend fun updateAccount(
        token: String,
        username: String? = null,
        email: String? = null,
        fcmToken: String? = null
    ): UserDto

    suspend fun updateProfileImage(token: String, image: ByteArray): UserDto
    suspend fun deleteAccount(token: String, password: String)
    suspend fun deleteFcmToken(token: String)
    suspend fun deleteProfileImage(token: String): UserDto
}