package com.tongminhnhut.android_compose.core.domain

sealed interface NetworkError : DomainError {
    object REQUEST_TIMEOUT : NetworkError
    object TOO_MANY_REQUESTS : NetworkError
    object NO_INTERNET : NetworkError
    object SERVER_ERROR : NetworkError
    object SERIALIZATION : NetworkError
    object UNKNOWN : NetworkError
}

fun NetworkError.toThrowable(): Throwable = when (this) {
    NetworkError.REQUEST_TIMEOUT -> java.net.SocketTimeoutException("Request timeout")
    NetworkError.TOO_MANY_REQUESTS -> retrofit2.HttpException(
        retrofit2.Response.error<Any>(
            429,
            okhttp3.ResponseBody.create(null, "")
        )
    )

    NetworkError.NO_INTERNET -> java.net.UnknownHostException("No internet")
    NetworkError.SERVER_ERROR -> java.io.IOException("Server error")
    NetworkError.SERIALIZATION -> kotlinx.serialization.SerializationException("Parse error")
    NetworkError.UNKNOWN -> Exception("Unknown network error")
}


