package com.tongminhnhut.android_compose.core.data.networking

import com.tongminhnhut.android_compose.core.domain.NetworkError
import   com.tongminhnhut.android_compose.core.domain.Result

suspend inline fun <T> safeCallApi(
    crossinline apiCall: suspend () -> T
): Result<T, NetworkError> {
    return try {
        val response = apiCall()
        Result.Success(response)
    } catch (e: Exception) {
        Result.Error(mapToNetworkError(e))
    }
}

fun mapToNetworkError(e: Exception): NetworkError {
    return when (e) {
        is java.net.SocketTimeoutException -> NetworkError.REQUEST_TIMEOUT
        is java.net.UnknownHostException -> NetworkError.NO_INTERNET
        is java.io.IOException -> NetworkError.NO_INTERNET
        is retrofit2.HttpException -> when (e.code()) {
            429 -> NetworkError.TOO_MANY_REQUESTS
            in 500..599 -> NetworkError.SERVER_ERROR
            else -> NetworkError.UNKNOWN
        }

        is kotlinx.serialization.SerializationException -> NetworkError.SERIALIZATION
        else -> NetworkError.UNKNOWN
    }
}

