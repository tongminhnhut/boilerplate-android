package com.tongminhnhut.android_compose.compose_app.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tongminhnhut.android_compose.compose_app.data.preference.PreferenceKeys
import com.tongminhnhut.android_compose.compose_app.data.preference.SecurePreferenceHelper
import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.mapper.toUser
import com.tongminhnhut.android_compose.compose_app.domain.usecase.auth.LoginUseCase
import com.tongminhnhut.android_compose.compose_app.presentation.login.model.LoginUiModel
import com.tongminhnhut.android_compose.core.domain.onError
import com.tongminhnhut.android_compose.core.domain.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val securePrefs: SecurePreferenceHelper
) : ViewModel() {
    private val _loginUiModel = MutableStateFlow<LoginUiModel?>(null)
    val loginUiModel get() = _loginUiModel.asStateFlow()

    fun onLogin() {
        viewModelScope.launch {
            loginUseCase.invoke(username = "", password = "").onSuccess { responseDto ->
                _loginUiModel.value = LoginUiModel(user = responseDto.toUser())
                storageToken(responseDto.token)
            }.onError {
                _loginUiModel.value = loginUiModel.value?.copy(status = it)
                Timber.tag("LOGIN ERROR:").e(it.name)
            }
        }
    }

    private fun storageToken(token: String) {
        securePrefs.putString(PreferenceKeys.TOKEN, token)
    }
}