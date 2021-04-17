package com.example.selfloanapps.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.selfloanapps.repository.MainRepository
import com.example.selfloanapps.ui.login.LoginViewModel

class LoginViewModelFactory(
    private val repository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}