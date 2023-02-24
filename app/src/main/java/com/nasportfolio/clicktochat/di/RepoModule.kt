package com.nasportfolio.clicktochat.di

import com.nasportfolio.clicktochat.data.auth.AuthRepositoryImpl
import com.nasportfolio.clicktochat.data.messages.MessageRepositoryImpl
import com.nasportfolio.clicktochat.data.users.UserRepositoryImpl
import com.nasportfolio.clicktochat.domain.auth.AuthRepository
import com.nasportfolio.clicktochat.domain.messages.MessageRepository
import com.nasportfolio.clicktochat.domain.users.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun bindsUserRepo(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindsAuthRepo(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsMessageRepo(
        messageRepositoryImpl: MessageRepositoryImpl
    ): MessageRepository
}