package com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote

import com.tongminhnhut.android_compose.compose_app.data.preference.SecurePreferenceHelper
import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.dto.UserResponseDto
import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.mapper.toUser
import com.tongminhnhut.android_compose.compose_app.domain.repository.auth.remote.AuthRepository
import com.tongminhnhut.android_compose.core.data.firebase.FirebaseService
import com.tongminhnhut.android_compose.core.data.networking.ApiService
import com.tongminhnhut.android_compose.core.data.networking.safeCallApi
import com.tongminhnhut.android_compose.core.domain.NetworkError
import com.tongminhnhut.android_compose.core.domain.XResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val securePreferenceHelper: SecurePreferenceHelper,
    private val firebaseService: FirebaseService
) : AuthRepository {
    override suspend fun login(
        username: String,
        password: String
    ): XResult<UserResponseDto, NetworkError> {
        return safeCallApi {
            apiService.login()
            val response = apiService.login()
            securePreferenceHelper.saveUser(response.toUser())
            response
        }
    }

    override suspend fun logout(): XResult<UserResponseDto, NetworkError> {
        return safeCallApi {
            // TODO: It's sample. Need to update api logout and logic clear user
            securePreferenceHelper.clear()
            UserResponseDto(id = "", name = "", token = "")
        }
    }

    override suspend fun loginWithGoogle(idToken: String): XResult<UserResponseDto, NetworkError> {
        return try {
            firebaseService.signOut()
            val signInRequest = firebaseService.firebaseSignInWithGoogle(idToken)
            val userData = UserResponseDto(
                id = signInRequest.uid,
                name = signInRequest.displayName ?: "",
                token = ""
            )
            securePreferenceHelper.saveUser(userData.toUser())
            XResult.Success(userData)
        } catch (e: Exception) {
            XResult.Error(NetworkError.SERVER_ERROR)
        }
    }


}