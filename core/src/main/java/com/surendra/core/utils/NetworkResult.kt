package com.surendra.core.utils


sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error<T>(val message: String, val throwable: Throwable? = null) : NetworkResult<T>()
    class Loading<T> : NetworkResult<T>()
}

inline fun <T> NetworkResult<T>.onSuccess(action: (T) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) action(data)
    return this
}

inline fun <T> NetworkResult<T>.onError(action: (String, Throwable?) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Error) action(message, throwable)
    return this
}

inline fun <T> NetworkResult<T>.onLoading(action: () -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Loading) action()
    return this
}