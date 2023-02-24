package com.nasportfolio.clicktochat.domain.utils

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data)
    class Failure<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}