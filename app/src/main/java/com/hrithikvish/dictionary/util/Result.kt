package com.hrithikvish.dictionary.util

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Result<T>(data = data)
    class Error<T>(message: String) : Result<T>(null, message)
    class Loading<T>(val isLoading: Boolean = true) : Result<T>(null)
}