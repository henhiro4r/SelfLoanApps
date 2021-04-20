package com.example.selfloanapps.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.selfloanapps.models.local.User

class PrefsManagerHelper() : PrefsManager {

    companion object {
        private const val USER_EMAIL = "user_email"
        private const val USER_NAME = "user_name"
        private const val USER_NIM = "user_nim"
        private const val USER_MAJOR = "user_major"
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"

        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: PrefsManagerHelper? = null
        private var LOCK = Any()

        operator fun invoke(context: Context): PrefsManagerHelper = instance ?: synchronized(LOCK) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context): PrefsManagerHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return PrefsManagerHelper()
        }
    }

    override fun getEmail(): String {
        return prefs?.getString(USER_EMAIL, "").toString()
    }

    override fun getAccessToken(): String {
        return prefs?.getString(ACCESS_TOKEN, "").toString()
    }

    override fun getRefreshToken(): String {
        return prefs?.getString(REFRESH_TOKEN, "").toString()
    }

    override fun getName(): String {
        return prefs?.getString(USER_NAME, "").toString()
    }

    override fun getNim(): String {
        return prefs?.getString(USER_NIM, "").toString()
    }

    override fun getMajor(): String {
        return prefs?.getString(USER_MAJOR, "").toString()
    }

    override suspend fun storeData(
        user: User,
        tokenType: String,
        accessToken: String,
        refreshToken: String
    ) {
        prefs?.edit(commit = true) {
            putString(USER_EMAIL, user.email)
            putString(USER_NAME, user.name)
            putString(USER_MAJOR, user.major)
            putString(USER_NIM, user.nim)
            putString(ACCESS_TOKEN, "$tokenType $accessToken")
            putString(REFRESH_TOKEN, refreshToken)
        }
    }

    fun clearPref() {
        prefs?.edit(commit = true) {
            clear()
        }
    }
}
