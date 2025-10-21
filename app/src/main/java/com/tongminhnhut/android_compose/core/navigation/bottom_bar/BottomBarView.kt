package com.tongminhnhut.android_compose.core.navigation.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.tongminhnhut.android_compose.core.navigation.Screen
import com.tongminhnhut.android_compose.core.navigation.currentRoute

@Composable
fun BottomNavigationUI(navController: NavController) {
    NavigationBar {
        bottoms.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = "")
                },
                label = { Text(text = item.title) },
                selected = currentRoute(navController) == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                })
        }
    }
}

class BottomItemModel(
    val icon: ImageVector,
    val title: String,
    val route: String,
)

val bottoms = listOf(
    BottomItemModel(icon = Icons.Default.Home, title = "Home", route = Screen.Home.route),
    BottomItemModel(
        icon = Icons.Default.Settings,
        title = "Setting",
        route = Screen.Setting.route,
    ),
    BottomItemModel(icon = Icons.Default.Email, title = "Message", route = Screen.Chat.route),
    BottomItemModel(icon = Icons.Default.Person, title = "Profile", route = Screen.Profile.route),
)