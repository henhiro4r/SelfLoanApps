package com.example.selfloanapps.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.selfloanapps.models.remote.ActiveLoanResponse
import com.example.selfloanapps.models.remote.HistoryResponse
import com.example.selfloanapps.models.remote.TapHistoryResponse
import com.example.selfloanapps.models.remote.UserResponse
import com.example.selfloanapps.repository.MainRepository
import com.example.selfloanapps.utils.PrefsManagerHelper
import com.example.selfloanapps.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SelfLoanViewModel(
    app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app) {

    private var prefsManager = PrefsManagerHelper(getApplication())
    private val token: String = prefsManager.getAccessToken()
    val currentLoan: MutableLiveData<Resource<ActiveLoanResponse>> = MutableLiveData()
    val historyLoan: MutableLiveData<Resource<HistoryResponse>> = MutableLiveData()
    val tapHistory: MutableLiveData<Resource<TapHistoryResponse>> = MutableLiveData()

    init {
        getLoan()
        getHistory()
        getTapHistory()
    }

    fun getLoan() = viewModelScope.launch {
        currentLoan.postValue(Resource.Loading())
        val response = repository.getLoan(token)
        currentLoan.postValue(handleLoanResponse(response))
    }

    fun getHistory() = viewModelScope.launch {
        historyLoan.postValue(Resource.Loading())
        val response = repository.getHistory(token)
        historyLoan.postValue(handleHistoryResponse(response))
    }

    fun getTapHistory() = viewModelScope.launch {
        tapHistory.postValue(Resource.Loading())
        val response = repository.getTapHistory(token)
        tapHistory.postValue(handleTapResponse(response))
    }

    private fun handleLoanResponse(response: Response<ActiveLoanResponse>): Resource<ActiveLoanResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleHistoryResponse(response: Response<HistoryResponse>): Resource<HistoryResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleTapResponse(response: Response<TapHistoryResponse>): Resource<TapHistoryResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}