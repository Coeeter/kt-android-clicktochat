package com.nasportfolio.clicktochat.data.messages.remote.socket

import com.nasportfolio.clicktochat.data.messages.remote.dtos.SocketRequest
import com.nasportfolio.clicktochat.data.messages.remote.dtos.SocketResponse
import com.nasportfolio.clicktochat.data.messages.remote.dtos.SocketResponseType
import com.nasportfolio.clicktochat.data.messages.remote.dtos.UrlDto
import com.nasportfolio.clicktochat.data.utils.createAuthorizationHeader
import com.nasportfolio.clicktochat.utils.baseUrl
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class KtorMessageSocket @Inject constructor(
    private val httpClient: HttpClient
) : MessageSocket {
    private var typingUsers: List<String> = emptyList()
    private var webSocketSession: WebSocketSession? = null

    override fun observeEvent(): Flow<SocketEvent> {
        return try {
            webSocketSession?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as Frame.Text).readText()
                    val dto = Json.decodeFromString<SocketResponse>(json)
                    when (dto.type) {
                        SocketResponseType.CREATE_MESSAGE -> {
                            SocketEvent.MessageReceived(dto.message!!)
                        }
                        SocketResponseType.UPDATE_MESSAGE -> {
                            SocketEvent.MessageReceived(dto.message!!)
                        }
                        SocketResponseType.SEEN_MESSAGES -> {
                            SocketEvent.MessagesSeen(dto.messagesSeen!!)
                        }
                        SocketResponseType.USER_TYPING -> {
                            if (dto.senderId!! !in typingUsers)
                                typingUsers = typingUsers + dto.senderId
                            SocketEvent.UsersTyping(typingUsers)
                        }
                        SocketResponseType.USER_STOP_TYPING -> {
                            if (dto.senderId!! in typingUsers)
                                typingUsers = typingUsers - dto.senderId
                            SocketEvent.UsersTyping(typingUsers)
                        }
                    }
                } ?: flow {}
        } catch (e: Exception) {
            e.printStackTrace()
            flow {}
        }
    }

    override suspend fun connectToSocket(token: String) {
        val path = httpClient.get("$baseUrl/api/messages/authenticate") {
            createAuthorizationHeader(token)
        }.body<UrlDto>().url
        webSocketSession = httpClient.webSocketSession {
            url("$baseUrl$path")
        }
    }

    override suspend fun createMessage(receiverId: String, message: String, imageUrl: String) {
        try {
            val request = SocketRequest.create(
                receiverId = receiverId,
                message = message,
                imageUrl = imageUrl
            )
            val json = Json.encodeToString(request)
            webSocketSession?.send(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun updateMessage(receiverId: String, messageId: String, message: String) {
        try {
            val request = SocketRequest.update(
                receiverId = receiverId,
                message = message,
                messageId = messageId
            )
            val json = Json.encodeToString(request)
            webSocketSession?.send(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun setUserTyping(receiverId: String) {
        try {
            val request = SocketRequest.typing(
                receiverId = receiverId,
            )
            val json = Json.encodeToString(request)
            webSocketSession?.send(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun setUserStopTyping(receiverId: String) {
        try {
            val request = SocketRequest.stopTyping(
                receiverId = receiverId,
            )
            val json = Json.encodeToString(request)
            webSocketSession?.send(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun setMessagesSeen(receiverId: String, messagesSeen: List<String>) {
        try {
            val request = SocketRequest.messagesSeen(
                receiverId = receiverId,
                messages = messagesSeen
            )
            val json = Json.encodeToString(request)
            webSocketSession?.send(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}