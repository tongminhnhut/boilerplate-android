package com.tongminhnhut.android_compose.compose_app.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tongminhnhut.android_compose.compose_app.presentation.login.viewmodel.LoginViewModel
import com.tongminhnhut.android_compose.core.navigation.AppCoordinator

@Composable
fun ProfileScreen(rootNavController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Button(
                content = {
                    Text("Logout")
                },

                onClick = {
                    viewModel.logout()
                    AppCoordinator.logout(rootNavController)
                })
        }

    }

}