package com.example.selfloanapps.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.selfloanapps.models.local.User

class PrefsManagerHelper(context: Context) {

    companion object {
        private const val USER_EMAIL = "user_email"
        private const val USER_NAME = "user_name"
        private const val USER_NIM = "user_nim"
        private const val USER_MAJOR = "user_major"
        private const val ACCESS_TOKEN = "access_token"
        private const val APP_PREFS = "app_prefs"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)


    fun getEmail() {
        preferences.getString(USER_EMAIL, "")
    }

    fun getAccessToken(): String {
        return preferences.getString(ACCESS_TOKEN, "").toString()
    }

    fun getName(): String {
        return preferences.getString(USER_NAME, "").toString()
    }

    fun getNim(): String {
        return preferences.getString(USER_NIM, "").toString()
    }

    fun getMajor(): String {
        return preferences.getString(USER_MAJOR, "").toString()
    }

    fun storeData(
        user: User?,
        tokenType: String?,
        accessToken: String?,
    ) {
        preferences.edit()
            .putString(USER_EMAIL, user?.email)
            .putString(USER_NAME, user?.name)
            .putString(USER_MAJOR, user?.major)
            .putString(USER_NIM, user?.nim)
            .putString(ACCESS_TOKEN, "$tokenType $accessToken")
            .apply()
    }

    fun clearPref() {
        preferences.edit()
            .clear()
            .apply()
    }
}
