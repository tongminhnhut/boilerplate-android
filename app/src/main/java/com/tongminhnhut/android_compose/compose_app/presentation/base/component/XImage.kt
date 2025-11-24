package com.tongminhnhut.android_compose.compose_app.presentation.base.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageScope
import coil.request.ImageRequest

@Composable
fun XImageView(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    cornerRadius: Int = 12,
    placeholderRes: Int? = null,
    errorRes: Int? = null
) {
    val shape = RoundedCornerShape(cornerRadius.dp)
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = modifier.clip(shape),
        contentScale = contentScale,
        loading = {
            DefaultLoadingContent(modifier.clip(shape))
        },
        error = {
            DefaultErrorContent(modifier.clip(shape))
        },
        success = { painter ->
            Image(
                painter = painter.painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = contentScale,
            )
        }
    )
}

@Composable
private fun SubcomposeAsyncImageScope.DefaultLoadingContent(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .background(color = Color.Gray)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            strokeWidth = 4.dp
        )
    }
}

@Composable
private fun SubcomposeAsyncImageScope.DefaultErrorContent(
    modifier: Modifier

) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.Warning,
            contentDescription = "Image error",
            tint = Color.Gray,
            modifier = Modifier.size(42.dp)
        )
    }
}