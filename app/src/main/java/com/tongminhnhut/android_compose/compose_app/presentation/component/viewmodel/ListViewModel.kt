package com.tongminhnhut.android_compose.compose_app.presentation.component.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tongminhnhut.android_compose.compose_app.domain.repository.user.remote.UserRepository
import com.tongminhnhut.android_compose.compose_app.presentation.base.paging.MoviePagingSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val pagingFactory: MoviePagingSourceFactory
) : ViewModel() {

    val moviePagingFlow = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 20, enablePlaceholders = true, ),
        pagingSourceFactory = {
            pagingFactory.create(userRepository)
        }).flow.cachedIn(viewModelScope)
}