package com.nasportfolio.clicktochat.data.auth.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePasswordDto(
    val password: String,
    val oldPassword: String,
)