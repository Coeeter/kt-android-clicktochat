package com.nasportfolio.clicktochat.data.utils

import com.nasportfolio.clicktochat.domain.utils.Resource
import kotlinx.coroutines.flow.*

inline fun <Result, Response> networkBoundResource(
    crossinline getFromLocal: () -> Flow<Result>,
    crossinline getFromRemote: suspend () -> Response,
    crossinline saveFetchedResult: suspend (Response) -> Unit,
    crossinline shouldFetch: (Result) -> Boolean = { true }
) = flow<Resource<Result>> {
    emit(Resource.Loading())
    val result = getFromLocal().first()
    val flow = if (shouldFetch(result)) {
        emit(Resource.Loading(result))
        try {
            saveFetchedResult(getFromRemote())
            getFromLocal().map { Resource.Success(it) }
        } catch (e: Exception) {
            getFromLocal().map { Resource.Failure(e, it) }
        }
    } else {
        getFromLocal().map { Resource.Success(it) }
    }
    emitAll(flow)
}