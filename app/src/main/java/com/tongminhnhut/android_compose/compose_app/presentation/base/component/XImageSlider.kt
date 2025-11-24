package com.tongminhnhut.android_compose.compose_app.presentation.base.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun XImageSlider(
    imageUrls: List<String>,
    modifier: Modifier = Modifier,
    cornerRadius: Int = 12
) {
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            XImageView(
                imageUrl = imageUrls[page],
                modifier = Modifier
                    .fillMaxWidth().height(200.dp),
                cornerRadius = cornerRadius
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        ) {
            repeat(imageUrls.size) { index ->
                val isSelected = pagerState.currentPage == index
                Surface(
                    color = if (isSelected) Color.White else Color.LightGray.copy(alpha = 0.6f),
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (isSelected) 10.dp else 8.dp),
                    shape = androidx.compose.foundation.shape.CircleShape
                ) {}
            }
        }
    }
}
