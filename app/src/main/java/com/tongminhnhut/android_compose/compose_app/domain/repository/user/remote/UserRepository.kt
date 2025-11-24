package com.tongminhnhut.android_compose.compose_app.domain.repository.user.remote

import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.dto.UserResponseDto
import com.tongminhnhut.android_compose.compose_app.presentation.base.paging.MovieDto
import com.tongminhnhut.android_compose.compose_app.presentation.base.paging.PagingResponse
import com.tongminhnhut.android_compose.core.domain.NetworkError
import  com.tongminhnhut.android_compose.core.domain.XResult


interface UserRepository {
    suspend fun getUsers(
        page: Int,
        limit: Int
    ): XResult<List<UserResponseDto>, NetworkError>

    suspend fun getMovies(
        page: Int,
        apiKey: String
    ): XResult<PagingResponse<MovieDto>, NetworkError>
}