package com.tongminhnhut.android_compose.compose_app.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tongminhnhut.android_compose.compose_app.data.preference.PreferenceKeys
import com.tongminhnhut.android_compose.compose_app.data.preference.SecurePreferenceHelper
import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.mapper.toUser
import com.tongminhnhut.android_compose.compose_app.domain.repository.auth.remote.AuthRepository
import com.tongminhnhut.android_compose.compose_app.domain.usecase.auth.LoginUseCase
import com.tongminhnhut.android_compose.compose_app.presentation.login.model.LoginUiModel
import com.tongminhnhut.android_compose.core.data.firebase.FirebaseService
import com.tongminhnhut.android_compose.core.domain.onError
import com.tongminhnhut.android_compose.core.domain.onSuccess
import com.tongminhnhut.android_compose.core.navigation.AppCoordinator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val securePrefs: SecurePreferenceHelper,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _loginUiModel = MutableStateFlow<LoginUiModel?>(null)
    val loginUiModel get() = _loginUiModel.asStateFlow()


    private val _inputEmail = MutableStateFlow("")
    val inputEmail get() = _inputEmail.asStateFlow()

    private val _inputPassword = MutableStateFlow("")
    val inputPassword get() = _inputPassword.asStateFlow()

    fun onLogin() {
        viewModelScope.launch {
            loginUseCase.invoke(username = "", password = "").onSuccess { responseDto ->
                _loginUiModel.value = LoginUiModel(user = responseDto.toUser())
                storageToken(responseDto.token)
            }.onError {
                _loginUiModel.value = loginUiModel.value?.copy(status = it)
            }
        }
    }

    private fun storageToken(token: String) {
        securePrefs.putString(PreferenceKeys.TOKEN, token)
    }

    fun onChangeEmail(value: String) {
        _inputEmail.value = value
    }

    fun onChangePassword(value: String) {
        _inputPassword.value = value
    }

    fun signInWithGoogle(idToken: String, navController: NavController) {
        viewModelScope.launch {
            val request = authRepository.loginWithGoogle(idToken = idToken)
            request.onSuccess {
                AppCoordinator.pushToMainScreen(navController)
            }.onError {
                Timber.tag("Error Sign Google").e(it.toString())
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}