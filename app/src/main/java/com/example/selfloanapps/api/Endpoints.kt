package com.example.selfloanapps.api

import com.example.selfloanapps.models.remote.ActiveLoanResponse
import com.example.selfloanapps.models.remote.HistoryResponse
import com.example.selfloanapps.models.remote.TapHistoryResponse
import com.example.selfloanapps.models.remote.UserResponse
import com.example.selfloanapps.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface Endpoints {

    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    suspend fun login(@Field("email") email: String, @Field("password") password: String): Response<UserResponse>

    @POST(Constants.LOGOUT_URL)
    suspend fun logout(@Header("Authorization") bearerToken: String)

    @POST(Constants.LOGOUT_URL)
    suspend fun blockCard(@Header("Authorization") bearerToken: String)

    @GET(Constants.ACTIVE_LOAN_URL)
    suspend fun getLoan(@Header("Authorization") bearerToken: String): Response<ActiveLoanResponse>

    @GET(Constants.HISTORY_LOAN_URL)
    suspend fun getHistory(@Header("Authorization") bearerToken: String): Response<HistoryResponse>

    @GET(Constants.TAP_HISTORY_URL)
    suspend fun getTapHistory(@Header("Authorization") bearerToken: String): Response<TapHistoryResponse>
}