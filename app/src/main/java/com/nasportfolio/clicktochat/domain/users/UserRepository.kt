package com.nasportfolio.clicktochat.domain.users

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    suspend fun searchForUsersByUsername(username: String): List<User>
    suspend fun getUserById(id: String): User
    suspend fun updateAccount(
        token: String,
        username: String? = null,
        email: String? = null,
        fcmToken: String? = null
    ): User
    suspend fun updateProfileImage(token: String, image: ByteArray): User
    suspend fun deleteAccount(token: String, password: String)
    suspend fun deleteFcmToken(token: String)
    suspend fun deleteProfileImage(token: String): User
}