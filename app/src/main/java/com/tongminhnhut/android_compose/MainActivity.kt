package com.tongminhnhut.android_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tongminhnhut.android_compose.compose_app.data.preference.SecurePreferenceHelper
import com.tongminhnhut.android_compose.core.navigation.AppNavigationGraph
import com.tongminhnhut.android_compose.ui.theme.AndroidCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var securePrefs: SecurePreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCleanArchitectureTheme {
                AppNavigationGraph( isLoggedIn = securePrefs.getUser() != null)
            }
        }
    }
}