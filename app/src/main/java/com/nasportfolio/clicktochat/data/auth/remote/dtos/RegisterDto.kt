package com.nasportfolio.clicktochat.data.auth.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDto(
    val username: String,
    val email: String,
    val password: String,
    val fcmToken: String,
)