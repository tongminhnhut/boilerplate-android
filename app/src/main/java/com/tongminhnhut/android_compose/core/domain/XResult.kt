package com.tongminhnhut.android_compose.core.domain


typealias DomainError = Error

sealed interface XResult<out D, out E : Error> {
    data class Success<out D>(val data: D) : XResult<D, Nothing>
    data class Error<out E : DomainError>(val error: E) : XResult<Nothing, E>
}

inline fun <T, E : Error, R> XResult<T, E>.map(map: (T) -> R): XResult<R, E> {
    return when (this) {
        is XResult.Error -> XResult.Error(error)
        is XResult.Success -> XResult.Success(map(data))
    }
}

fun <T, E : Error> XResult<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map { }
}

inline fun <T, E : Error> XResult<T, E>.onSuccess(action: (T) -> Unit): XResult<T, E> {
    return when (this) {
        is XResult.Error -> this
        is XResult.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, E : Error> XResult<T, E>.onError(action: (E) -> Unit): XResult<T, E> {
    return when (this) {
        is XResult.Error -> {
            action(error)
            this
        }

        is XResult.Success -> this
    }
}

typealias EmptyResult<E> = XResult<Unit, E>