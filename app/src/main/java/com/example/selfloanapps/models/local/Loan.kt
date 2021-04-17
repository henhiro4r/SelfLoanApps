package com.example.selfloanapps.models.local

data class Loan(
    val id: Int?,
    val book: MutableList<Book>,
    val returnDate: String?,
    val dueDate: String?,
    val loanDate: String?,
    val fine: String?,
    val status: Boolean?
)