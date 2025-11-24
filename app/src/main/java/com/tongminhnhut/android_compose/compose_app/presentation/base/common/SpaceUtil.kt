package com.tongminhnhut.android_compose.compose_app.presentation.base.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerTinySmall() {
    Spacer(modifier = Modifier.size( 4.dp))
}

@Composable
fun SpacerSmall() {
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
fun SpacerNormal() {
    Spacer(modifier = Modifier.padding(vertical = 16.dp))
}

@Composable
fun SpacerLarge() {
    Spacer(modifier = Modifier.padding(vertical = 24.dp))
}