package com.tongminhnhut.android_compose.core.navigation

import androidx.navigation.NavController

object AppCoordinator {

    fun pushToSignUpScreen(navController: NavController){
        navController.navigate(Screen.SignUp.route)
    }

    fun pushToMainScreen(navController: NavController){
        navController.navigate(Screen.MainApp.route){
            popUpTo("auth"){inclusive = true}
        }
    }

    fun logout(navController: NavController){
        navController.navigate(Screen.Login.route){
            popUpTo("main"){inclusive = true}
        }
    }
}