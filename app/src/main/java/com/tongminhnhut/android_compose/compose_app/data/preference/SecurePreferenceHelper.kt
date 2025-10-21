package com.tongminhnhut.android_compose.compose_app.data.preference

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit
import com.google.gson.Gson
import com.tongminhnhut.android_compose.compose_app.data.preference.PreferenceKeys.USER
import com.tongminhnhut.android_compose.compose_app.domain.model.auth.User

@Singleton
class SecurePreferenceHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val PREFERENCE_NAME = "secure_prefs"
    }

    private val prefs by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            PREFERENCE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun putString(key: String, value: String) {
        prefs.edit { putString(key, value) }
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return prefs.getString(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        prefs.edit { putInt(key, value) }
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return prefs.getInt(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        prefs.edit { putBoolean(key, value) }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    fun remove(key: String) {
        prefs.edit { remove(key) }
    }

    fun saveUser(user: User) {
        putObject(USER, user)
    }

    fun getUser(): User? {
        return getObject(USER)
    }

    fun clear() {
        prefs.edit { clear() }
    }

    private val gson = Gson()

    fun <T> putObject(key: String, obj: T) {
        val json = gson.toJson(obj)
        prefs.edit { putString(key, json) }
    }

    private inline fun <reified T> getObject(key: String): T? {
        val json = prefs.getString(key, null) ?: return null
        return try {
            gson.fromJson(json, T::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
