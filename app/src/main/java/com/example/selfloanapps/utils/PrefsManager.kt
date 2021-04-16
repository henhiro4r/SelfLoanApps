package com.example.selfloanapps.utils

import kotlinx.coroutines.flow.Flow

interface PrefsManager {
    fun getEmail(): Flow<String>
    fun getPassword(): Flow<String>
    fun getAccessToken(): Flow<String>
    fun getRefreshToken(): Flow<String>

    suspend fun storeData(email: String, password: String, accessToken: String, refreshToken: String)
}