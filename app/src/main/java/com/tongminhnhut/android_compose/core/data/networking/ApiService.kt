package com.tongminhnhut.android_compose.core.data.networking

import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.dto.UserResponseDto
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(): UserResponseDto
}