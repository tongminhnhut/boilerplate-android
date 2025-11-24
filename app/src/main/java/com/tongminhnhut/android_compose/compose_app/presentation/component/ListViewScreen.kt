package com.tongminhnhut.android_compose.compose_app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.tongminhnhut.android_compose.compose_app.presentation.base.common.SpacerTinySmall
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.AppBarView
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.XImageView
import com.tongminhnhut.android_compose.compose_app.presentation.base.paging.MovieDto
import com.tongminhnhut.android_compose.compose_app.presentation.component.viewmodel.ListViewModel
import com.tongminhnhut.android_compose.ui.theme.AppTextStyle

@Composable
fun ListViewScreen(rootNavController: NavController) {
    val listViewModel: ListViewModel = hiltViewModel()

    val lazyPagingItems = listViewModel.moviePagingFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppBarView(title = "List View", onBackClick = rootNavController::popBackStack)
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(
                count = lazyPagingItems.itemCount,
                key = {
                    (lazyPagingItems[it]?.id ?: it) + it
                }
            ) { index ->
                val movie = lazyPagingItems[index]

                movie?.let {
                    MovieItem(it)
                }
            }

            // Loading / Error
            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { CircularProgressIndicator() }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { CircularProgressIndicator() }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = loadState.refresh as LoadState.Error

//                        item {
//                            ErrorItem(
//                                message = e.error.message ?: "Error loading data",
//                                onRetry = { retry() }
//                            )
//                        }
                    }
                }
            }
        }
    }

}

@Composable
fun MovieItem(movie: MovieDto) {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
    ) {
        XImageView(imageUrl = movie.imagePoster, modifier = Modifier.size(50.dp))
        SpacerTinySmall()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = movie.title,
                style = AppTextStyle.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            SpacerTinySmall()
            Text(
                text = movie.overview,
                style = AppTextStyle.Small,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }


    }

}

@Preview
@Composable
private fun PreviewContent(modifier: Modifier = Modifier) {
//    UserItem(user = UserResponseDto(id = "", name = "Dustin", token = ""))
}