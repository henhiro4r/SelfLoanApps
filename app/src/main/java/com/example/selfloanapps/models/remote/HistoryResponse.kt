package com.example.selfloanapps.models.remote

import com.example.selfloanapps.models.local.Loan

data class HistoryResponse(
    val loanHistories: MutableList<Loan>,
    val count: Int
)