package com.example.selfloanapps.utils

sealed class LoginUiState {
    object Success : LoginUiState()
    object Loading : LoginUiState()
    object Empty : LoginUiState()
    data class Error(val message : String) : LoginUiState()
}