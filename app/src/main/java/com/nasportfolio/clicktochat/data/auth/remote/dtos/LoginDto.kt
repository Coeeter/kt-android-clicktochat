package com.nasportfolio.clicktochat.data.auth.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val email: String,
    val password: String
)