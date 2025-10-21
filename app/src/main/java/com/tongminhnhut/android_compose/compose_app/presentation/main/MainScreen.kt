package com.tongminhnhut.android_compose.compose_app.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tongminhnhut.android_compose.compose_app.presentation.home.HomeScreen
import com.tongminhnhut.android_compose.compose_app.presentation.profile.ProfileScreen
import com.tongminhnhut.android_compose.core.navigation.Screen
import com.tongminhnhut.android_compose.core.navigation.bottom_bar.BottomNavigationUI

/**
 * Use rootNavHostController without bottom bar
 * Use navController with bottom bar
 */
@Composable
fun MainScreen(modifier: Modifier = Modifier, rootNavHostController: NavController) {
    val navController: NavHostController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigationUI(navController)

    }) { paddingValues ->
        NavHost(
            modifier = modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screen.Home.route
        ) {

            composable(route = Screen.Home.route, content = {
                HomeScreen(rootNavController = rootNavHostController)
            })

            composable(route = Screen.Setting.route, content = {
                HomeScreen(rootNavController = rootNavHostController)
            })

            composable(route = Screen.Chat.route, content = {
                HomeScreen(rootNavController = rootNavHostController)
            })

            composable(route = Screen.Profile.route, content = {
                ProfileScreen(rootNavController = rootNavHostController)
            })
        }
    }
}
