package com.example.selfloanapps.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.selfloanapps.models.remote.UserResponse
import com.example.selfloanapps.repository.MainRepository
import com.example.selfloanapps.utils.LoginUiState
import com.example.selfloanapps.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(
    val repository: MainRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginUiState.value = LoginUiState.Loading
        val response = repository.login(email, password)
        _loginUiState.value = handleLoginResponse(response)
    }

    private fun handleLoginResponse(response: Response<UserResponse>): LoginUiState {
        if (response.isSuccessful) {
            response.body()?.let { results ->
                //save to shared prefs
                return LoginUiState.Success
            }
        }

        return LoginUiState.Error(response.message())
    }
}