package com.example.selfloanapps.models.remote

import com.example.selfloanapps.models.local.Loan
import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("data")
    val loanHistories: MutableList<Loan>,
    val count: Int
)