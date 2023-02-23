package com.nasportfolio.clicktochat.data.users.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UpdateAccountDto(
    val username: String? = null,
    val email: String? = null,
    val fcmToken: String? = null
)