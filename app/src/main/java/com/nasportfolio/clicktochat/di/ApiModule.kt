package com.nasportfolio.clicktochat.di

import com.nasportfolio.clicktochat.data.auth.remote.AuthApi
import com.nasportfolio.clicktochat.data.auth.remote.KtorAuthApi
import com.nasportfolio.clicktochat.data.messages.remote.api.KtorMessageApi
import com.nasportfolio.clicktochat.data.messages.remote.api.MessageApi
import com.nasportfolio.clicktochat.data.messages.remote.socket.KtorMessageSocket
import com.nasportfolio.clicktochat.data.messages.remote.socket.MessageSocket
import com.nasportfolio.clicktochat.data.users.remote.KtorUserApi
import com.nasportfolio.clicktochat.data.users.remote.UserApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {
    @Binds
    abstract fun bindsAuthApi(ktorAuthApi: KtorAuthApi): AuthApi

    @Binds
    abstract fun bindsUserApi(ktorUserApi: KtorUserApi): UserApi

    @Binds
    abstract fun bindsMessageApi(ktorMessageApi: KtorMessageApi): MessageApi

    @Binds
    @Singleton
    abstract fun bindsMessageSocket(ktorMessageSocket: KtorMessageSocket): MessageSocket
}