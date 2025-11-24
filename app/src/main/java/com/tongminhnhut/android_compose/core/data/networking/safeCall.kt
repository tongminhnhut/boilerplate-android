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
import com.tongminhnhut.android_compose.core.domain.XResult

inline fun <reified T> safeCallFlow(
    crossinline execute: suspend () -> HttpResponse
): Flow<XResult<T, NetworkError>> = flow {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        emit(XResult.Error(NetworkError.NO_INTERNET))
        return@flow
    } catch (e: SerializationException) {
        emit(XResult.Error(NetworkError.SERIALIZATION))
        return@flow
    } catch (e: Exception) {
        kotlin.coroutines.coroutineContext.ensureActive()
        emit(XResult.Error(NetworkError.UNKNOWN))
        return@flow
    }

    val result: XResult<T, NetworkError> = responseToResult(response)
    emit(result)

}.flowOn(Dispatchers.IO)