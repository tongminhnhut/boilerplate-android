package com.tongminhnhut.android_compose.core.data.networking

import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.dto.UserResponseDto
import com.tongminhnhut.android_compose.compose_app.presentation.base.paging.MovieDto
import com.tongminhnhut.android_compose.compose_app.presentation.base.paging.PagingResponse
import com.tongminhnhut.android_compose.core.domain.NetworkError
import com.tongminhnhut.android_compose.core.domain.XResult
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(): UserResponseDto

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): XResult<List<UserResponseDto>, NetworkError>

    @GET("3/movie/popular")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String,
    ): PagingResponse<MovieDto>
}