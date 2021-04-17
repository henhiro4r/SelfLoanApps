package com.example.selfloanapps.ui.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.selfloanapps.repository.MainRepository

class SelfLoanViewModelFactory(
    val app: Application,
    private val repository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SelfLoanViewModel(app, repository) as T
    }
}