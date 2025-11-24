package com.tongminhnhut.android_compose.core.navigation

import androidx.navigation.NavController

object AppCoordinator {

    fun pushToSignUpScreen(navController: NavController) {
        navController.navigate(Screen.SignUp.route)
    }

    fun pushToMainScreen(navController: NavController) {
        navController.navigate(Screen.MainApp.route) {
            popUpTo("auth") { inclusive = true }
        }
    }

    fun logout(navController: NavController) {
        navController.navigate(Screen.Login.route) {
            popUpTo("main") { inclusive = true }
        }
    }

    fun pushToComponentScreen(navController: NavController) {
        navController.navigate(Screen.Components.route)
    }

    fun pushToInputScreen(navController: NavController) {
        navController.navigate(Screen.Input.route)
    }

    fun pushToButtonScreen(navController: NavController) {
        navController.navigate(Screen.Button.route)
    }

    fun pushToDropDownScreen(navController: NavController) {
        navController.navigate(Screen.DropDown.route)
    }

    fun pushToImageViewScreen(navController: NavController) {
        navController.navigate(Screen.ImageView.route)
    }

    fun pushToListViewScreen(navController: NavController) {
        navController.navigate(Screen.ListView.route)
    }
}