package com.surendra.core.utils


inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (isSuccess) action(getOrThrow())
    return this
}

inline fun <T> Result<T>.onFailure(action: (exception: Throwable) -> Unit): Result<T> {
    exceptionOrNull()?.let(action)
    return this
}

inline fun <T, R> Result<T>.mapCatching(transform: (T) -> R): Result<R> {
    return try {
        if (isSuccess) {
            Result.success(transform(getOrThrow()))
        } else {
            Result.failure(exceptionOrNull()!!)
        }
    } catch (e: Throwable) {
        Result.failure(e)
    }
}