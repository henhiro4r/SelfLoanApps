package com.example.selfloanapps.utils

import com.example.selfloanapps.models.local.User

interface PrefsManager {
    fun getEmail()
    fun getAccessToken()
    fun getRefreshToken()
    fun getName()
    fun getNim()
    fun getMajor()

    fun storeData(user: User, tokenType: String, accessToken: String)
}