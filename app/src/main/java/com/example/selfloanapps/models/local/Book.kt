package com.example.selfloanapps.models.local

import com.google.gson.annotations.SerializedName

data class Book(
    val isbn: String?,
    val title: String?,
    @SerializedName("publisher_name")
    val publisher: String,
)
