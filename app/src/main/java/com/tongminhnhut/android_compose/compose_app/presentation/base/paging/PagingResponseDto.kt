package com.tongminhnhut.android_compose.compose_app.presentation.base.paging

data class PagingResponse<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
)