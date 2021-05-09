package com.example.selfloanapps.utils

class Constants {
    companion object {
        const val BASE_URL = "http://192.168.1.69/SelfLoans/selfloan-server/public/api/"
        const val LOGIN_URL = "login"
        const val LOGOUT_URL = "logout"
        const val ACTIVE_LOAN_URL = "active-loan"
        const val HISTORY_LOAN_URL = "history-loan"
        const val TAP_HISTORY_URL = "tap-history"
        const val BLOCK_CARD_URL = "block-card"
        const val EXTEND_URL = "extend/{id}"
        const val RETURN_URL = "return/{id}"
    }
}