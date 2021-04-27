package com.example.selfloanapps.models.remote

import com.example.selfloanapps.models.local.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("token_type")
    val tokenType: String?,

    @SerializedName("user")
    val user: User?,

    @SerializedName("token")
    val accessToken: String?,

    @SerializedName("message")
    val message: String?
)