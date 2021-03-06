package com.example.selfloanapps.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.selfloanapps.models.local.User
import androidx.preference.PreferenceManager

class PrefsManagerHelper {

    companion object {
        private const val USER_EMAIL = "user_email"
        private const val USER_NAME = "user_name"
        private const val USER_NIM = "user_nim"
        private const val USER_MAJOR = "user_major"
        private const val ACCESS_TOKEN = "access_token"
        private const val APP_PREFS = "app_prefs"

        private var prefs: SharedPreferences? = null

        @Volatile
        private var instance: PrefsManagerHelper? = null
        private var LOCK = Any()

        operator fun invoke(context: Context): PrefsManagerHelper = instance ?: synchronized(LOCK) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context) : PrefsManagerHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return PrefsManagerHelper()
        }
    }

//    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
//    private var editor: SharedPreferences.Editor = preferences.edit()

//
//    fun getEmail() {
//        preferences.getString(USER_EMAIL, "")
//    }

    fun getAccessToken(): String {
        return prefs?.getString(ACCESS_TOKEN, "").toString()
    }

    fun getName(): String {
        return prefs?.getString(USER_NAME, "").toString()
    }

    fun getNim(): String {
        return prefs?.getString(USER_NIM, "").toString()
    }

    fun getMajor(): String {
        return prefs?.getString(USER_MAJOR, "").toString()
    }

    fun storeData(
        user: User?,
        tokenType: String?,
        accessToken: String?,
    ) {
        prefs?.edit(commit = true) {
            putString(USER_EMAIL, user?.email)
            putString(USER_NAME, user?.name)
            putString(USER_MAJOR, user?.major)
            putString(USER_NIM, user?.nim)
            putString(ACCESS_TOKEN, "$tokenType $accessToken")
        }
    }

    fun clearPref() {
        prefs?.edit(commit = true) {
            clear()
        }
    }
}
