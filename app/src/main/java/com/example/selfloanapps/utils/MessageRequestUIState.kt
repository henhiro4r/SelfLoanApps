package com.example.selfloanapps.utils

import com.example.selfloanapps.models.remote.MessageOnlyResponse

sealed class MessageRequestUIState {
    data class Success(val response: MessageOnlyResponse) : MessageRequestUIState()
    object Loading : MessageRequestUIState()
    object Empty : MessageRequestUIState()
    data class Error(val message : String) : MessageRequestUIState()
}