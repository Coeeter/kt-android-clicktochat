package com.nasportfolio.clicktochat.data.messages

import com.nasportfolio.clicktochat.domain.messages.Message
import com.nasportfolio.clicktochat.domain.messages.MessageRepository
import kotlinx.coroutines.flow.Flow

class MessageRepositoryImpl: MessageRepository {
    override fun getAllMessagesOfChat(token: String, otherUserId: String): Flow<List<Message>> {
        TODO("Not yet implemented")
    }

    override fun getAllMessagesOfUser(token: String): Flow<List<Message>> {
        TODO("Not yet implemented")
    }

    override suspend fun uploadImage(token: String, image: ByteArray): String {
        TODO("Not yet implemented")
    }

    override suspend fun connectToSocket(token: String) {
        TODO("Not yet implemented")
    }

    override fun observeUsersWhoAreTyping(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun createMessage(receiverId: String, message: String, imageUrl: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMessage(receiverId: String, messageId: String, message: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setUserTyping(receiverId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setUserStopTyping(receiverId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setMessagesSeen(receiverId: String, messagesSeen: List<String>) {
        TODO("Not yet implemented")
    }

}