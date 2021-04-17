package com.example.selfloanapps.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.selfloanapps.models.remote.ActiveLoanResponse
import com.example.selfloanapps.models.remote.HistoryResponse
import com.example.selfloanapps.models.remote.TapHistoryResponse
import com.example.selfloanapps.models.remote.UserResponse
import com.example.selfloanapps.repository.MainRepository
import com.example.selfloanapps.utils.Resource

class SelfLoanViewModel(
    app: Application,
    val repository: MainRepository
) : AndroidViewModel(app) {

    val currentLoan: MutableLiveData<Resource<ActiveLoanResponse>> = MutableLiveData()
    val historyLoan: MutableLiveData<Resource<HistoryResponse>> = MutableLiveData()
    val userData: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val tapHistory: MutableLiveData<Resource<TapHistoryResponse>> = MutableLiveData()
}