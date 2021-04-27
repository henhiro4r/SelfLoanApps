package com.example.selfloanapps.models.local

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Loan(
    val id: String?,

    @SerializedName("books")
    val book: List<Book>,

    @SerializedName("return_date")
    val returnDate: String?,

    @SerializedName("due_date")
    val dueDate: String?,

    @SerializedName("loan_date")
    val loanDate: String?,

    @SerializedName("days_left")
    val daysLeft: String?,

    val fine: String?,
    val status: String?
) : Serializable