package com.example.selfloanapps.utils

import com.example.selfloanapps.models.remote.UserResponse

sealed class LoginUiState {
    data class Success(val response: UserResponse) : LoginUiState()
    object Loading : LoginUiState()
    object Empty : LoginUiState()
    data class Error(val message : String) : LoginUiState()
}