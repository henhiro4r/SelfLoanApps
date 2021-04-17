package com.example.selfloanapps.models.remote

import com.example.selfloanapps.models.local.Loan

data class ActiveLoanResponse(
    val activeLoan: MutableList<Loan>,
    val count: Int
)