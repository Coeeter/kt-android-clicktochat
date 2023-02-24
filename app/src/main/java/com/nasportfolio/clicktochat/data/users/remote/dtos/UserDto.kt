package com.nasportfolio.clicktochat.data.users.remote.dtos

import com.nasportfolio.clicktochat.data.users.local.UserEntity
import com.nasportfolio.clicktochat.domain.users.User
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val createdAtTimestamp: Long,
    val imageUrl: String? = null,
) {
    fun toExternalUser(): User {
        return User(
            id = id,
            username = username,
            email = email,
            createdAtTimestamp = createdAtTimestamp,
            imageUrl = imageUrl
        )
    }

    fun toUserEntity(): UserEntity {
        return UserEntity(
            id = id,
            username = username,
            email = email,
            createdAtTimestamp = createdAtTimestamp,
            imageUrl = imageUrl
        )
    }
}

