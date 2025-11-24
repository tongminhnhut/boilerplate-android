package com.tongminhnhut.android_compose.core.data.networking


import com.tongminhnhut.android_compose.core.domain.NetworkError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import com.tongminhnhut.android_compose.core.domain.XResult


suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): XResult<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                XResult.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                XResult.Error(NetworkError.SERIALIZATION)
            }
        }

        408 -> XResult.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> XResult.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> XResult.Error(NetworkError.SERVER_ERROR)
        else -> XResult.Error(NetworkError.UNKNOWN)
    }
}