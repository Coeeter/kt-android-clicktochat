package com.nasportfolio.clicktochat.data.users.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg userEntity: UserEntity)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}