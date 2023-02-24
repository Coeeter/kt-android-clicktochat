package com.nasportfolio.clicktochat.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.nasportfolio.clicktochat.data.auth.local.AuthPreferences
import com.nasportfolio.clicktochat.data.auth.local.AuthSharedPreferences
import com.nasportfolio.clicktochat.data.database.ClcDatabase
import com.nasportfolio.clicktochat.data.messages.local.MessageDao
import com.nasportfolio.clicktochat.data.users.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    fun providesSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesClcDatabase(
        @ApplicationContext context: Context
    ): ClcDatabase = Room.databaseBuilder(
        context,
        ClcDatabase::class.java,
        "clicktochat_db"
    ).build()

    @Provides
    @Singleton
    fun providesUserDao(
        clcDatabase: ClcDatabase
    ): UserDao = clcDatabase.getUserDao()

    @Provides
    @Singleton
    fun providesMessageDao(
        clcDatabase: ClcDatabase
    ): MessageDao = clcDatabase.getMessageDao()

    @Provides
    @Singleton
    fun providesAuthPreferences(
        authSharedPreferences: AuthSharedPreferences
    ): AuthPreferences = authSharedPreferences
}