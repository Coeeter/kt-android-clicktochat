package com.nasportfolio.clicktochat.data.auth.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TokenDto(
    val token: String
)