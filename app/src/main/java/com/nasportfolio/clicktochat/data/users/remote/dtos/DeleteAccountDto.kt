package com.nasportfolio.clicktochat.data.users.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class DeleteAccountDto(
    val password: String
)