package com.tongminhnhut.android_compose.compose_app.presentation.login

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tongminhnhut.android_compose.compose_app.presentation.base.common.SpacerNormal
import com.tongminhnhut.android_compose.compose_app.presentation.base.common.SpacerSmall
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.XButton
import com.tongminhnhut.android_compose.compose_app.presentation.base.component.XInput
import com.tongminhnhut.android_compose.compose_app.presentation.login.viewmodel.LoginViewModel
import com.tongminhnhut.android_compose.core.navigation.AppCoordinator
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import timber.log.Timber

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavController) {
    val loginViewModel: LoginViewModel = hiltViewModel()

    val loginUiModel by loginViewModel.loginUiModel.collectAsStateWithLifecycle()
    val emailValue by loginViewModel.inputEmail.collectAsStateWithLifecycle()
    val passwordValue by loginViewModel.inputPassword.collectAsStateWithLifecycle()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {

                XInput(
                    isRequired = true,
                    label = "Email",
                    value = emailValue,
                    onValueChange = loginViewModel::onChangeEmail,
                    placeholder = "example@mail.com",
                    keyboardType = KeyboardType.Email,
                    maxLines = 1
                )

                SpacerNormal()

                XInput(
                    isRequired = true,
                    label = "Password",
                    value = passwordValue,
                    onValueChange = loginViewModel::onChangePassword,
                    placeholder = "••••••••",
                    keyboardType = KeyboardType.Password,
                    maxLines = 1
                )

                SpacerNormal()

                XButton(
                    text = "Login",
                    onClick = {
                        AppCoordinator.pushToMainScreen(navController)
                    }
                )

                SignInWithGoogle(viewModel = loginViewModel, navController = navController)
            }
        }
    }

}

@Composable
fun SignInWithGoogle(viewModel: LoginViewModel, navController: NavController) {
    val context = LocalContext.current
    val activity = context as Activity

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("303892952474-8c9mnci8r22qrhkrcf5peio1gemjv63k.apps.googleusercontent.com")
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(activity, gso)

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

            try {
                val account = task.getResult(ApiException::class.java)
                val idToken = account.idToken ?: ""


                viewModel.signInWithGoogle(idToken = idToken, navController = navController)

            } catch (e: Exception) {
                Timber.tag("GoogleSignIn").e("Login with Google Firebase failed: $e")
            }
        }

    Column {
        SpacerSmall()
        XButton(
            text = "Google",
            onClick = {
                googleSignInClient.signOut()
                launcher.launch(googleSignInClient.signInIntent)
            },
            iconLeft = Icons.Default.Email
        )
    }
}