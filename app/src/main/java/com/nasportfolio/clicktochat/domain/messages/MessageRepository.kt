package com.nasportfolio.clicktochat.domain.messages

import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getAllMessagesOfChat(token: String, otherUserId: String): Flow<List<Message>>
    fun getAllMessagesOfUser(token: String): Flow<List<Message>>
    suspend fun uploadImage(token: String, image: ByteArray): String
    suspend fun connectToSocket(token: String)
    fun observeUsersWhoAreTyping(): Flow<List<String>>
    suspend fun createMessage(receiverId: String, message: String, imageUrl: String)
    suspend fun updateMessage(receiverId: String, messageId: String, message: String)
    suspend fun setUserTyping(receiverId: String)
    suspend fun setUserStopTyping(receiverId: String)
    suspend fun setMessagesSeen(receiverId: String, messagesSeen: List<String>)
}