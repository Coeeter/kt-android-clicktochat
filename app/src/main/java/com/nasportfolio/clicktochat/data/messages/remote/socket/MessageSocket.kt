package com.nasportfolio.clicktochat.data.messages.remote.socket

import kotlinx.coroutines.flow.Flow

interface MessageSocket {
    fun observeEvent(): Flow<SocketEvent>
    suspend fun connectToSocket(token: String)
    suspend fun createMessage(receiverId: String, message: String, imageUrl: String)
    suspend fun updateMessage(receiverId: String, messageId: String, message: String)
    suspend fun setUserTyping(receiverId: String)
    suspend fun setUserStopTyping(receiverId: String)
    suspend fun setMessagesSeen(receiverId: String, messagesSeen: List<String>)
}