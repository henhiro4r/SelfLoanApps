package com.example.selfloanapps.models.remote

import com.example.selfloanapps.models.local.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("token_type")
    val tokenType: String?,

    @SerializedName("expires_in")
    val expireIn: Int?,

    @SerializedName("access_token")
    val accessToken: String?,

    @SerializedName("refresh_token")
    val refreshToken: String?,

    @SerializedName("user_data")
    val user: User?,

    @SerializedName("message")
    val message: String?
)