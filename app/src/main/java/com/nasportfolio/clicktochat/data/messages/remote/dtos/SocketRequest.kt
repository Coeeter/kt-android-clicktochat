package com.nasportfolio.clicktochat.data.messages.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class SocketRequest(
    val type: SocketRequestType,
    val createMessageRequest: CreateMessageRequest? = null,
    val updateMessageRequest: UpdateMessageRequest? = null,
    val receiverId: String? = null,
    val messageSeenRequest: MessageSeenRequest? = null,
) {
    companion object {
        fun create(
            receiverId: String,
            message: String,
            imageUrl: String? = null
        ): SocketRequest {
            return SocketRequest(
                type = SocketRequestType.CREATE,
                createMessageRequest = CreateMessageRequest(
                    receiverId = receiverId,
                    message = message,
                    imageUrl = imageUrl
                )
            )
        }

        fun update(
            receiverId: String,
            messageId: String,
            message: String,
        ): SocketRequest {
            return SocketRequest(
                type = SocketRequestType.UPDATE,
                updateMessageRequest = UpdateMessageRequest(
                    receiverId = receiverId,
                    messageId = messageId,
                    message = message
                )
            )
        }

        fun typing(receiverId: String): SocketRequest {
            return SocketRequest(
                type = SocketRequestType.TYPING,
                receiverId = receiverId
            )
        }

        fun stopTyping(receiverId: String): SocketRequest {
            return SocketRequest(
                type = SocketRequestType.STOP_TYPING,
                receiverId = receiverId
            )
        }

        fun messagesSeen(
            receiverId: String,
            messages: List<String>
        ): SocketRequest {
            return SocketRequest(
                type = SocketRequestType.MESSAGE_SEEN,
                messageSeenRequest = MessageSeenRequest(
                    receiverId = receiverId,
                    messages = messages
                )
            )
        }
    }
}

enum class SocketRequestType {
    CREATE,
    UPDATE,
    TYPING,
    STOP_TYPING,
    MESSAGE_SEEN
}

@Serializable
data class CreateMessageRequest(
    val receiverId: String,
    val message: String,
    val imageUrl: String? = null
)

@Serializable
data class UpdateMessageRequest(
    val receiverId: String,
    val messageId: String,
    val message: String,
)

@Serializable
data class MessageSeenRequest(
    val receiverId: String,
    val messages: List<String>
)