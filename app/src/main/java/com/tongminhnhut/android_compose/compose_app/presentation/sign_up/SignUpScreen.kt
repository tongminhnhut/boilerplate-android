package com.tongminhnhut.android_compose.compose_app.presentation.sign_up

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {

    Scaffold { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            Button(onClick = {}) {
                Text("Sign Up")
            }
        }

    }

}