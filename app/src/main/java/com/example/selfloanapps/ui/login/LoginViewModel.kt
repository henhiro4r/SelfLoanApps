package com.example.selfloanapps.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.selfloanapps.models.remote.UserResponse
import com.example.selfloanapps.repository.MainRepository
import com.example.selfloanapps.utils.LoginUiState
import com.example.selfloanapps.utils.PrefsManagerHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(
    app: Application,
    val repository: MainRepository
) : AndroidViewModel(app) {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    private var preferenceHelper = PrefsManagerHelper(getApplication())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginUiState.value = LoginUiState.Loading
        val response = repository.login(email, password)
        _loginUiState.value = handleLoginResponse(response)
    }

    private fun handleLoginResponse(response: Response<UserResponse>): LoginUiState {
        if (response.isSuccessful) {
            response.body()?.let { results ->
                if (results.user != null
                    && results.tokenType?.isNotEmpty() == true
                    && results.accessToken?.isNotEmpty() == true
                    && results.refreshToken?.isNotEmpty() == true
                ) {
                    viewModelScope.launch {
                        preferenceHelper.storeData(
                            results.user,
                            results.tokenType,
                            results.accessToken,
                            results.refreshToken
                        )
                    }
                }
                return LoginUiState.Success
            }
        }

        return LoginUiState.Error(response.message())
    }

    override fun onCleared() {
        super.onCleared()
    }
}