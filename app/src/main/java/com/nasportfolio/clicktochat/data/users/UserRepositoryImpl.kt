package com.nasportfolio.clicktochat.data.users

import com.nasportfolio.clicktochat.domain.users.User
import com.nasportfolio.clicktochat.domain.users.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl: UserRepository {
    override fun getAllUsers(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchForUsersByUsername(username: String): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(id: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun updateAccount(
        token: String,
        username: String?,
        email: String?,
        fcmToken: String?
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfileImage(token: String, image: ByteArray): User {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccount(token: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFcmToken(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProfileImage(token: String): User {
        TODO("Not yet implemented")
    }
}