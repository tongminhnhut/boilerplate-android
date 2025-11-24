package com.tongminhnhut.android_compose.compose_app.presentation.base.paging

import com.tongminhnhut.android_compose.BuildConfig

data class MovieDto(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
){
    val imagePoster get(): String{
        return BuildConfig.BASE_URL + poster_path
    }
}
