package com.nasportfolio.clicktochat.data.messages.remote.dtos

import com.nasportfolio.clicktochat.data.messages.local.MessageEntity
import com.nasportfolio.clicktochat.domain.messages.Message
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val id: String,
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

    fun toMessageEntity(): MessageEntity {
        return MessageEntity(
            id = id,
            senderId = senderId,
            receiverId = receiverId,
            message = message,
            createdAtTimestamp = createdAtTimestamp,
            updatedAtTimestamp = updatedAtTimestamp,
            imageUrl = imageUrl,
            seen = seen
        )
    }
}