package com.nasportfolio.clicktochat.domain.utils

sealed class Resource<T> {
    class Success<T>(val data: T): Resource<T>()
    class Failure<T>(val message: String): Resource<T>()
    class Loading<T>(val isLoading: Boolean): Resource<T>()
}