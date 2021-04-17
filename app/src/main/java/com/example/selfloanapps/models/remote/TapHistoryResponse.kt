package com.example.selfloanapps.models.remote

import com.example.selfloanapps.models.local.TapHistory

data class TapHistoryResponse(
    val tapHistories: MutableList<TapHistory>,
    val count: Int
)