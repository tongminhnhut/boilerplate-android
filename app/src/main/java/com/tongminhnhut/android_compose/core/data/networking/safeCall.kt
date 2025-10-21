package com.tongminhnhut.android_compose.core.data.networking

import com.tongminhnhut.android_compose.core.domain.NetworkError
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException
import com.tongminhnhut.android_compose.core.domain.Result

inline fun <reified T> safeCallFlow(
    crossinline execute: suspend () -> HttpResponse
): Flow<Result<T, NetworkError>> = flow {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        emit(Result.Error(NetworkError.NO_INTERNET))
        return@flow
    } catch (e: SerializationException) {
        emit(Result.Error(NetworkError.SERIALIZATION))
        return@flow
    } catch (e: Exception) {
        kotlin.coroutines.coroutineContext.ensureActive()
        emit(Result.Error(NetworkError.UNKNOWN))
        return@flow
    }

    val result: Result<T, NetworkError> = responseToResult(response)
    emit(result)

}.flowOn(Dispatchers.IO)