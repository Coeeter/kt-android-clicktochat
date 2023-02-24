package com.nasportfolio.clicktochat.data.users

import com.nasportfolio.clicktochat.data.users.local.UserDao
import com.nasportfolio.clicktochat.data.users.remote.UserApi
import com.nasportfolio.clicktochat.data.utils.networkBoundResource
import com.nasportfolio.clicktochat.domain.users.User
import com.nasportfolio.clicktochat.domain.users.UserRepository
import com.nasportfolio.clicktochat.domain.utils.Resource
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userApi: UserApi,
) : UserRepository {
    override fun getAllUsers(fetchFromRemote: Boolean) = networkBoundResource(
        getFromLocal = {
            userDao.getAllUsers().map { userList ->
                userList.map { it.toExternalUser() }
            }
        },
        getFromRemote = { userApi.getAllUsers() },
        saveFetchedResult = { result ->
            userDao.deleteAllUsers()
            userDao.insertUser(*result.map { it.toUserEntity() }.toTypedArray())
        },
        shouldFetch = { fetchFromRemote || it.isEmpty() }
    )

    override suspend fun searchForUsersByUsername(username: String): Resource<List<User>> {
        return try {
            val users = userApi.searchForUsersByUsername(username).map {
                it.toExternalUser()
            }
            Resource.Success(users)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun getUserById(id: String): Resource<User> {
        return try {
            val user = userApi.getUserById(id).toExternalUser()
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun updateAccount(
        token: String,
        username: String?,
        email: String?,
        fcmToken: String?
    ): Resource<User> {
        return try {
            val user = userApi.updateAccount(
                token = token,
                username = username,
                email = email,
                fcmToken = fcmToken
            )
            userDao.insertUser(user.toUserEntity())
            Resource.Success(user.toExternalUser())
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun updateProfileImage(token: String, image: ByteArray): Resource<User> {
        return try {
            val user = userApi.updateProfileImage(token = token, image = image)
            userDao.insertUser(user.toUserEntity())
            Resource.Success(user.toExternalUser())
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun deleteAccount(token: String, password: String) {
        userApi.deleteAccount(token = token, password = password)
        userDao.deleteAllUsers()
        val users = userApi.getAllUsers()
            .map { it.toUserEntity() }
            .toTypedArray()
        userDao.insertUser(*users)
    }

    override suspend fun deleteFcmToken(token: String) {
        userApi.deleteFcmToken(token)
    }

    override suspend fun deleteProfileImage(token: String): Resource<User> {
        return try {
            val user = userApi.deleteProfileImage(token)
            userDao.insertUser(user.toUserEntity())
            Resource.Success(user.toExternalUser())
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}