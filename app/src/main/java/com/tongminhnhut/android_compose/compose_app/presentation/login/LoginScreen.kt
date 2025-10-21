package com.tongminhnhut.android_compose.compose_app.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tongminhnhut.android_compose.compose_app.presentation.login.viewmodel.LoginViewModel
import com.tongminhnhut.android_compose.core.navigation.AppCoordinator

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavController) {
    val loginViewModel: LoginViewModel = hiltViewModel()

    val loginUiModel by loginViewModel.loginUiModel.collectAsStateWithLifecycle()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Login Screen", modifier = Modifier.clickable(onClick = {
                AppCoordinator.pushToMainScreen(navController)
            }))
        }
    }
}