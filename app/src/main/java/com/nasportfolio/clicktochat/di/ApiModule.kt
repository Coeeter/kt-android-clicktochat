package com.nasportfolio.clicktochat.di

import com.nasportfolio.clicktochat.data.auth.remote.AuthApi
import com.nasportfolio.clicktochat.data.auth.remote.KtorAuthApi
import com.nasportfolio.clicktochat.data.users.remote.KtorUserApi
import com.nasportfolio.clicktochat.data.users.remote.UserApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {
    @Binds
    abstract fun bindsAuthApi(ktorAuthApi: KtorAuthApi): AuthApi

    @Binds
    abstract fun bindsUserApi(ktorUserApi: KtorUserApi): UserApi
}