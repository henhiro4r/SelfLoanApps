package com.example.selfloanapps.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.selfloanapps.models.remote.ActiveLoanResponse
import com.example.selfloanapps.models.remote.HistoryResponse
import com.example.selfloanapps.models.remote.MessageOnlyResponse
import com.example.selfloanapps.models.remote.TapHistoryResponse
import com.example.selfloanapps.repository.MainRepository
import com.example.selfloanapps.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SelfLoanViewModel(
    app: Application,
    private val repository: MainRepository
) : AndroidViewModel(app) {

    private val TAG = "SelfLoanViewModel"
    val messageRequestUIState: MutableLiveData<Resource<MessageOnlyResponse>> = MutableLiveData()
    val currentLoan: MutableLiveData<Resource<ActiveLoanResponse>> = MutableLiveData()
    val historyLoan: MutableLiveData<Resource<HistoryResponse>> = MutableLiveData()
    val tapHistory: MutableLiveData<Resource<TapHistoryResponse>> = MutableLiveData()

    fun getLoan(token: String) = viewModelScope.launch {
        Log.d(TAG, "getLoan: $token")
        currentLoan.postValue(Resource.Loading())
        val response = repository.getLoan(token)
        currentLoan.postValue(handleLoanResponse(response))
    }

    fun getHistory(token: String) = viewModelScope.launch {
        historyLoan.postValue(Resource.Loading())
        val response = repository.getHistory(token)
        historyLoan.postValue(handleHistoryResponse(response))
    }

    fun getTapHistory(token: String) = viewModelScope.launch {
        tapHistory.postValue(Resource.Loading())
        val response = repository.getTapHistory(token)
        tapHistory.postValue(handleTapResponse(response))
    }

    private fun handleLoanResponse(response: Response<ActiveLoanResponse>): Resource<ActiveLoanResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                Log.d(TAG, "handleLoanResponse: ${resultResponse.toString()}")
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


    fun logout(token: String) = viewModelScope.launch {
        messageRequestUIState.postValue(Resource.Loading())
        val response = repository.logout(token)
        messageRequestUIState.postValue(handleMessageResponse(response))
    }

    fun blockCard(token: String) = viewModelScope.launch {
        messageRequestUIState.postValue(Resource.Loading())
        val response = repository.blockCard(token)
        messageRequestUIState.postValue(handleMessageResponse(response))
    }

    private fun handleMessageResponse(response: Response<MessageOnlyResponse>): Resource<MessageOnlyResponse> {
        if (response.isSuccessful) {
            response.body()?.let { results ->
                return Resource.Success(results)
            }
        }

        return Resource.Error(response.message())
    }
}