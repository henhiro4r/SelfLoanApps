package com.example.selfloanapps.utils

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow

private const val STORE_NAME = "user_profiles"

class PrefsStoreImpl(context: Context): PrefsManager {

    private val prefsName: String = "user_data"
    private lateinit var prefs: SharedPreferences

    init {

    }

    override fun getEmail(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getAccessToken(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getRefreshToken(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun storeData(
        email: String,
        password: String,
        accessToken: String,
        refreshToken: String
    ) {
        TODO("Not yet implemented")
    }

}
