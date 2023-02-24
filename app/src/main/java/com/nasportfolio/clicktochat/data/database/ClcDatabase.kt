package com.nasportfolio.clicktochat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nasportfolio.clicktochat.data.users.local.UserDao

@Database(
    entities = [UserDao::class],
    version = 1
)
abstract class ClcDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
}