package com.nasportfolio.clicktochat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient = HttpClient(Android) {
        expectSuccess = true
        install(ContentNegotiation) {
            json()
        }
        install(WebSockets)
    }
}