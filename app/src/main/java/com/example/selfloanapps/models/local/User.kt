package com.example.selfloanapps.models.local

data class User(
    val id: Int?,
    val nim: String?,
    val email: String?,
    val name: String?,
    val enrollmentYear: String?,
    val card: Card
)