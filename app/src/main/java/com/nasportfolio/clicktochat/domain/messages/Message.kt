package com.nasportfolio.clicktochat.domain.messages

data class Message(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val message: String,
    val createdAtTimestamp: Long,
    val isUpdated: Boolean,
    val imageUrl: String?,
    val seen: Boolean,
)