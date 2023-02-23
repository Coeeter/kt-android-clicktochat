package com.nasportfolio.clicktochat.data.users.remote.dtos

import com.nasportfolio.clicktochat.domain.users.User
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val createdAtTimestamp: Long,
    val imageUrl: String? = null,
)

fun User.toUserDto(): User {
    return User(
        id = id,
        username = username,
        email = email,
        createdAtTimestamp = createdAtTimestamp,
        imageUrl = imageUrl
    )
}