package com.example.selfloanapps.models.remote

import com.example.selfloanapps.models.local.Loan
import com.google.gson.annotations.SerializedName

data class ActiveLoanResponse(
    @SerializedName("data")
    val activeLoan: MutableList<Loan>,
    val count: Int
)