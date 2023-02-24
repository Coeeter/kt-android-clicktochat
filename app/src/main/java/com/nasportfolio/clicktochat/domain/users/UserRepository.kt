package com.nasportfolio.clicktochat.domain.users

import com.nasportfolio.clicktochat.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(fetchFromRemote: Boolean): Flow<Resource<List<User>>>
    suspend fun searchForUsersByUsername(username: String): Resource<List<User>>
    suspend fun getUserById(id: String): Resource<User>
    suspend fun updateAccount(
        token: String,
        username: String? = null,
        email: String? = null,
        fcmToken: String? = null
    ): Resource<User>
    suspend fun updateProfileImage(token: String, image: ByteArray): Resource<User>
    suspend fun deleteAccount(token: String, password: String)
    suspend fun deleteFcmToken(token: String)
    suspend fun deleteProfileImage(token: String): Resource<User>
}