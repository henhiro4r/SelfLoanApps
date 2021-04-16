package com.example.selfloanapps.network

import com.example.selfloanapps.models.remote.ActiveLoanResponse
import com.example.selfloanapps.models.remote.HistoryResponse
import com.example.selfloanapps.models.remote.UserResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoints {

    @POST("login")
    suspend fun login(@Field("email") email: String, @Field("password") password: String): UserResponse

    @POST("logout")
    suspend fun logout()

    @GET("active-loan")
    suspend fun getLoan(): List<ActiveLoanResponse>

    @GET("history-loan")
    suspend fun getHistory(): List<HistoryResponse>
}