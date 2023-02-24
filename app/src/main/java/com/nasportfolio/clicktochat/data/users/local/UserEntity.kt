package com.nasportfolio.clicktochat.data.users.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nasportfolio.clicktochat.domain.users.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
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
}