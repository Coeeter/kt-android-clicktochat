package com.nasportfolio.clicktochat.data.messages.remote.api

import com.nasportfolio.clicktochat.data.messages.remote.dtos.MessageDto

interface MessageApi {
    suspend fun getAllMessagesOfChat(token: String, otherUserId: String): List<MessageDto>
    suspend fun getAllMessagesOfUser(token: String): List<MessageDto>
    suspend fun uploadImage(token: String, image: ByteArray): String
}