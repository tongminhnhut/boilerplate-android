package com.tongminhnhut.android_compose.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.tongminhnhut.android_compose.compose_app.presentation.home.HomeScreen
import com.tongminhnhut.android_compose.compose_app.presentation.login.LoginScreen
import com.tongminhnhut.android_compose.compose_app.presentation.main.MainScreen
import com.tongminhnhut.android_compose.compose_app.presentation.sign_up.SignUpScreen

@Composable
fun AppNavigationGraph(
    isLoggedIn: Boolean
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = if (isLoggedIn) "main" else "auth") {
        authGraph(rootNavHostController = navController)
        mainController(rootNavHostController = navController)
    }
}

fun NavGraphBuilder.authGraph(rootNavHostController: NavHostController){
    navigation(startDestination = Screen.Login.route, route = "auth"){
        composable(route = Screen.Login.route, content = {
            LoginScreen(navController = rootNavHostController)
        })
    }
}

fun NavGraphBuilder.mainController(rootNavHostController: NavHostController){
    navigation(startDestination = Screen.MainApp.route, route = "main"){
        composable(route = Screen.MainApp.route, content = {
            MainScreen(rootNavHostController = rootNavHostController)
        })
        composable(route = Screen.SignUp.route){
            SignUpScreen()
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}