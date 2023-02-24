package com.nasportfolio.clicktochat.data.messages

import com.nasportfolio.clicktochat.data.messages.local.MessageDao
import com.nasportfolio.clicktochat.data.messages.remote.api.MessageApi
import com.nasportfolio.clicktochat.data.messages.remote.socket.MessageSocket
import com.nasportfolio.clicktochat.data.utils.networkBoundResource
import com.nasportfolio.clicktochat.domain.auth.AuthRepository
import com.nasportfolio.clicktochat.domain.messages.Message
import com.nasportfolio.clicktochat.domain.messages.MessageRepository
import com.nasportfolio.clicktochat.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messageApi: MessageApi,
    private val messageDao: MessageDao,
    private val messageSocket: MessageSocket,
    private val authRepository: AuthRepository,
) : MessageRepository {
    override fun getAllMessagesOfChat(
        token: String,
        otherUserId: String
    ): Flow<Resource<List<Message>>> = flow {
        val userId = authRepository.getUserId().data ?: return@flow
        val flow = networkBoundResource(
            getFromLocal = {
                messageDao.getAllMessagesOfUser(userId).map { list ->
                    list.map { it.toExternalModel() }.filter {
                        it.receiverId == otherUserId || it.senderId == otherUserId
                    }
                }
            },
            getFromRemote = {
                messageApi.getAllMessagesOfChat(token, otherUserId)
            },
            saveFetchedResult = { messageList ->
                val messages = messageList
                    .map { it.toMessageEntity() }
                    .toTypedArray()
                messageDao.insertMessages(*messages)
            }
        )
        emitAll(flow)
    }

    override fun getAllMessagesOfUser(token: String): Flow<Resource<List<Message>>> = flow {
        val userId = authRepository.getUserId().data ?: return@flow
        val flow = networkBoundResource(
            getFromLocal = {
                messageDao.getAllMessagesOfUser(userId = userId).map { list ->
                    list.map { it.toExternalModel() }
                }
            },
            getFromRemote = {
                messageApi.getAllMessagesOfUser(token)
            },
            saveFetchedResult = { messageList ->
                val messages = messageList
                    .map { it.toMessageEntity() }
                    .toTypedArray()
                messageDao.deleteAllMessages()
                messageDao.insertMessages(*messages)
            }
        )
        emitAll(flow)
    }

    override suspend fun uploadImage(token: String, image: ByteArray): Resource<String> {
        return try {
            val url = messageApi.uploadImage(token, image)
            Resource.Success(url)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun connectToSocket(token: String) {
        messageSocket.connectToSocket(token)
    }

    override fun observeUsersWhoAreTyping(): Flow<Resource<List<String>>> {
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