package com.example.selfloanapps.utils

import com.example.selfloanapps.models.local.User

interface PrefsManager {
    fun getEmail(): String
    fun getAccessToken(): String
    fun getRefreshToken(): String
    fun getName(): String
    fun getNim(): String
    fun getMajor(): String

    suspend fun storeData(user: User, tokenType: String, accessToken: String, refreshToken: String)
}