package com.tongminhnhut.android_compose.compose_app.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tongminhnhut.android_compose.core.navigation.AppCoordinator

@Composable
fun HomeScreen(modifier: Modifier = Modifier, rootNavController: NavController) {

    Scaffold { paddingValues ->
        Column(modifier = modifier
            .padding(paddingValues)
            .padding(16.dp)) {
            Button(onClick = {
                AppCoordinator.pushToComponentScreen(rootNavController)
            }) {
                Text("Open Components View")
            }
        }

    }

}