package com.tongminhnhut.android_compose.compose_app.domain.usecase.auth

import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.dto.UserResponseDto
import com.tongminhnhut.android_compose.compose_app.domain.repository.auth.remote.AuthRepository
import com.tongminhnhut.android_compose.core.domain.NetworkError
import com.tongminhnhut.android_compose.core.domain.Result


class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(
        username: String,
        password: String,
    ): Result<UserResponseDto, NetworkError> {
        return repository.login(username, password)
    }
}