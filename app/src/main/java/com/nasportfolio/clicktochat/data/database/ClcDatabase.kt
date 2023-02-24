package com.nasportfolio.clicktochat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nasportfolio.clicktochat.data.messages.local.MessageDao
import com.nasportfolio.clicktochat.data.messages.local.MessageEntity
import com.nasportfolio.clicktochat.data.users.local.UserDao
import com.nasportfolio.clicktochat.data.users.local.UserEntity

@Database(
    entities = [UserEntity::class, MessageEntity::class],
    version = 1
)
abstract class ClcDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getMessageDao(): MessageDao
}