package com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote

import com.tongminhnhut.android_compose.compose_app.data.preference.SecurePreferenceHelper
import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.dto.UserResponseDto
import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.mapper.toUser
import com.tongminhnhut.android_compose.compose_app.domain.repository.auth.remote.AuthRepository
import com.tongminhnhut.android_compose.core.data.networking.ApiService
import com.tongminhnhut.android_compose.core.data.networking.safeCallApi
import com.tongminhnhut.android_compose.core.domain.NetworkError
import com.tongminhnhut.android_compose.core.domain.Result

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val securePreferenceHelper: SecurePreferenceHelper,
) : AuthRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<UserResponseDto, NetworkError> {
        return safeCallApi {
            apiService.login()
            val response = apiService.login()
            securePreferenceHelper.saveUser(response.toUser())
            response
        }
    }

    override suspend fun logout(): Result<UserResponseDto, NetworkError> {
        return safeCallApi {
            // TODO: It's sample. Need to update api logout and logic clear user
            apiService.login()
            val response = apiService.login()
            securePreferenceHelper.clear()
            response
        }
    }
}