package com.nasportfolio.clicktochat.domain.users

import kotlinx.coroutines.flow.Flow
import java.io.File

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    suspend fun searchForUsersByUsername(username: String): List<User>
    suspend fun getUserById(id: String): User
    suspend fun updateAccount(
        username: String? = null,
        email: String? = null,
        fcmToken: String? = null
    ): User
    suspend fun updateProfileImage(file: File): User
    suspend fun deleteAccount(password: String)
    suspend fun deleteFcmToken()
    suspend fun deleteProfileImage()
}