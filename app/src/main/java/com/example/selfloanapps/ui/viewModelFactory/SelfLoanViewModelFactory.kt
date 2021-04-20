package com.example.selfloanapps.ui.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.selfloanapps.repository.MainRepository
import com.example.selfloanapps.ui.viewModel.SelfLoanViewModel

class SelfLoanViewModelFactory(
    val app: Application,
    private val repository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SelfLoanViewModel(app, repository) as T
    }
}