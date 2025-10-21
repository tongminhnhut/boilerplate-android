package com.tongminhnhut.android_compose.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.tongminhnhut.android_compose.R

enum class Route(val value: String) {
    LOGIN("login"),
    SIGN_UP("sign_up"),
    MAIN_APP("main_app"),
    HOME("home"),
    SETTING("setting"),
    PROFILE("profile"),
    CHAT("chat")

}

sealed class Screen(
    val route: String,
    @StringRes title: Int = R.string.app_name,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = ""
) {
    data object MainApp : Screen(Route.MAIN_APP.value)
    data object Home : Screen(Route.HOME.value)
    data object Setting : Screen(Route.SETTING.value)
    data object Chat : Screen(Route.CHAT.value)
    data object Profile : Screen(Route.PROFILE.value)
    data object Login : Screen(Route.LOGIN.value)
    data object SignUp : Screen(Route.SIGN_UP.value)


}