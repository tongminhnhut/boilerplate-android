package com.tongminhnhut.android_compose.core.di

import com.tongminhnhut.android_compose.compose_app.data.preference.SecurePreferenceHelper
import com.tongminhnhut.android_compose.compose_app.data.repository.auth.remote.AuthRepositoryImpl
import com.tongminhnhut.android_compose.compose_app.data.repository.user.remote.UserRepositoryImpl
import com.tongminhnhut.android_compose.compose_app.domain.repository.auth.remote.AuthRepository
import com.tongminhnhut.android_compose.compose_app.domain.repository.user.remote.UserRepository
import com.tongminhnhut.android_compose.core.data.firebase.FirebaseService
import com.tongminhnhut.android_compose.core.data.networking.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
        securePreferenceHelper: SecurePreferenceHelper,
        firebaseService: FirebaseService
    ): AuthRepository {
        return AuthRepositoryImpl(apiService, securePreferenceHelper, firebaseService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        apiService: ApiService
    ): UserRepository {
        return UserRepositoryImpl(apiService)
    }


}