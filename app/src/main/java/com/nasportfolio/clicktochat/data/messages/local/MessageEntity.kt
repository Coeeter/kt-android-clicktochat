package com.nasportfolio.clicktochat.data.messages.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nasportfolio.clicktochat.domain.messages.Message

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: String,
    val senderId: String,
    val receiverId: String,
    val message: String,
    val createdAtTimestamp: Long,
    val updatedAtTimestamp: Long,
    val imageUrl: String?,
    val seen: Boolean,
) {
    fun toExternalModel(): Message {
        return Message(
            id = id,
            senderId = senderId,
            receiverId = receiverId,
            message = message,
            createdAtTimestamp = createdAtTimestamp,
            isUpdated = createdAtTimestamp != updatedAtTimestamp,
            imageUrl = imageUrl,
            seen = seen
        )
    }
}