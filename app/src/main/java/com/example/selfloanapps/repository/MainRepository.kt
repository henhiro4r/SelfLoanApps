package com.example.selfloanapps.repository

import com.example.selfloanapps.api.RetrofitService

class MainRepository {

    suspend fun login(email: String, password: String, messageToken: String) =
        RetrofitService.api.login(email, password, messageToken)

    suspend fun getLoan(bearerToken: String) =
        RetrofitService.api.getLoan(bearerToken)

    suspend fun logout(bearerToken: String) =
        RetrofitService.api.logout(bearerToken)

    suspend fun getHistory(bearerToken: String) =
        RetrofitService.api.getHistory(bearerToken)

    suspend fun getTapHistory(bearerToken: String) =
        RetrofitService.api.getTapHistory(bearerToken)

    suspend fun blockCard(bearerToken: String) =
        RetrofitService.api.blockCard(bearerToken)
}