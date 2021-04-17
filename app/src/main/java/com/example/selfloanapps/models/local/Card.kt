package com.example.selfloanapps.models.local

data class Card(
    val id: Int?,
    val isBlocked: Boolean?,
    val lastTap: String,
    val tapHistory: MutableList<TapHistory>,
)
