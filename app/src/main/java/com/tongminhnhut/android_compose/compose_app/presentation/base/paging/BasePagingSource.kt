package com.tongminhnhut.android_compose.compose_app.presentation.base.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BasePagingSource<Value : Any>(
    private val loadFunction: suspend (LoadParams<Int>) -> LoadResult<Int, Value>
) : PagingSource<Int, Value>() {

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        return try {
            loadFunction(params)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}