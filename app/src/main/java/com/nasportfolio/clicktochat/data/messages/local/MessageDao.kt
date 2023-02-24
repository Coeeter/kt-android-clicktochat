package com.nasportfolio.clicktochat.data.messages.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query(
        """
        SELECT * 
        FROM messages 
        WHERE senderId = :userId OR receiverId = :userId 
        ORDER BY createdAtTimestamp DESC
        """
    )
    fun getAllMessagesOfUser(userId: String): Flow<List<MessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(vararg messageEntity: MessageEntity)

    @Query("DELETE FROM messages")
    suspend fun deleteAllMessages()
}