package com.example.selfloanapps.models.remote

import com.example.selfloanapps.models.local.TapHistory
import com.google.gson.annotations.SerializedName

data class TapHistoryResponse(
    @SerializedName("data")
    val tapContainer: List<TapContainer>
)

data class TapContainer(
    val id: Int,
    @SerializedName("tap_history")
    val tapHistory: List<TapHistory>
)