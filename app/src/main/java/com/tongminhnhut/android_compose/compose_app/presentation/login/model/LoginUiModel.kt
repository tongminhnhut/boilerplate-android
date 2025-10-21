package com.tongminhnhut.android_compose.compose_app.presentation.login.model

import androidx.compose.runtime.Stable
import com.tongminhnhut.android_compose.compose_app.domain.model.auth.User
import com.tongminhnhut.android_compose.core.domain.NetworkError

@Stable
data class LoginUiModel(
    val user: User,
    val status: NetworkError = NetworkError.UNKNOWN
)
