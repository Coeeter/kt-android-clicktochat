package com.nasportfolio.clicktochat.domain.users

data class User(
    val id: String,
    val username: String,
    val email: String,
    val createdAtTimestamp: Long,
    val imageUrl: String? = null,
)