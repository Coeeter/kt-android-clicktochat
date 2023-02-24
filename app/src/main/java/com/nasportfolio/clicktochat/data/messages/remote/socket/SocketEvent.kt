package com.nasportfolio.clicktochat.data.messages.remote.socket

import com.nasportfolio.clicktochat.data.messages.remote.dtos.MessageDto

sealed class SocketEvent {
    class MessageReceived(val message: MessageDto) : SocketEvent()
    class UsersTyping(val users: List<String>) : SocketEvent()
    class MessagesSeen(val messages: List<MessageDto>) : SocketEvent()
}