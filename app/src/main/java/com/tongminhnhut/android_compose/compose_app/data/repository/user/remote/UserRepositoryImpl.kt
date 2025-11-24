package com.tongminhnhut.android_compose.compose_app.data.repository.user.remote

import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.dto.UserResponseDto
import com.tongminhnhut.android_compose.compose_app.domain.repository.user.remote.UserRepository
import com.tongminhnhut.android_compose.compose_app.presentation.base.paging.MovieDto
import com.tongminhnhut.android_compose.compose_app.presentation.base.paging.PagingResponse
import com.tongminhnhut.android_compose.core.data.networking.ApiService
import com.tongminhnhut.android_compose.core.domain.NetworkError
import com.tongminhnhut.android_compose.core.domain.XResult
import com.tongminhnhut.android_compose.core.domain.onError
import com.tongminhnhut.android_compose.core.domain.onSuccess
import kotlinx.io.IOException
import retrofit2.HttpException

class UserRepositoryImpl(
    private val apiService: ApiService,
) : UserRepository {
    override suspend fun getUsers(
        page: Int,
        limit: Int
    ): XResult<List<UserResponseDto>, NetworkError> {
        return apiService.getUsers(page = page, limit = limit)
    }

    override suspend fun getMovies(
        page: Int,
        apiKey: String
    ): XResult<PagingResponse<MovieDto>, NetworkError> {
        return try {
            val response = apiService.getMovies(page, apiKey)
            XResult.Success(response)
        } catch (e: IOException) {
            XResult.Error(NetworkError.UNKNOWN)
        } catch (e: HttpException) {
            XResult.Error(NetworkError.SERVER_ERROR)
        } catch (e: Exception) {
            XResult.Error(NetworkError.SERVER_ERROR)
        }
    }
}