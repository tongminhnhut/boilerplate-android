package com.tongminhnhut.android_compose.compose_app.domain.repository.auth.remote

import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.dto.UserResponseDto
import com.tongminhnhut.android_compose.core.domain.NetworkError
import  com.tongminhnhut.android_compose.core.domain.Result

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<UserResponseDto, NetworkError>
    suspend fun logout(): Result<UserResponseDto, NetworkError>
}