package com.tongminhnhut.android_compose.compose_app.presentation.base.paging

import android.util.Log
import com.tongminhnhut.android_compose.compose_app.domain.repository.user.remote.UserRepository
import com.tongminhnhut.android_compose.core.domain.XResult
import com.tongminhnhut.android_compose.core.domain.toThrowable
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MoviePagingSource @AssistedInject constructor(@Assisted private val userRepository: UserRepository) :
    BasePagingSource<MovieDto>({ params ->
        val page = params.key ?: 1

        val result = userRepository.getMovies(page, "8914a9e74f5b55def1d3e4aa097c79d0")

        when (result) {
            is XResult.Success -> {
                val response = result.data
                Log.d("AAAAA size:", response.results.size.toString() + "-- page: ${response.page} --- totalPage: ${response.total_pages}")

                val nextKey =
                    if (page < response.total_pages) page + 1 else null

                LoadResult.Page(
                    data = response.results,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = nextKey
                )
            }

            is XResult.Error -> LoadResult.Error(result.error.toThrowable())
        }
    })


@AssistedFactory
interface MoviePagingSourceFactory {
    fun create(repository: UserRepository): MoviePagingSource
}