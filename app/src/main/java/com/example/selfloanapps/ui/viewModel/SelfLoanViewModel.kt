package com.example.selfloanapps.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.selfloanapps.models.remote.ActiveLoanResponse
import com.example.selfloanapps.models.remote.HistoryResponse
import com.example.selfloanapps.models.remote.TapHistoryResponse
import com.example.selfloanapps.models.remote.UserResponse
import com.example.selfloanapps.repository.MainRepository
import com.example.selfloanapps.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SelfLoanViewModel(
    app: Application,
    val repository: MainRepository
) : AndroidViewModel(app) {

    val currentLoan: MutableLiveData<Resource<ActiveLoanResponse>> = MutableLiveData()
    val historyLoan: MutableLiveData<Resource<HistoryResponse>> = MutableLiveData()
    val userData: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val tapHistory: MutableLiveData<Resource<TapHistoryResponse>> = MutableLiveData()

    fun getLoan(token: String) = viewModelScope.launch {
        currentLoan.postValue(Resource.Loading())
        val response = repository.getLoan(token)

    }

//    breakingNews.postValue(Resource.Loading())
//    val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
//    breakingNews.postValue(handleBreakingNewsResponse(response))

//    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
//        if(response.isSuccessful) {
//            response.body()?.let { resultResponse ->
//                breakingNewsPage++
//                if(breakingNewsResponse == null) {
//                    breakingNewsResponse = resultResponse
//                } else {
//                    val oldArticles = breakingNewsResponse?.articles
//                    val newArticles = resultResponse.articles
//                    oldArticles?.addAll(newArticles)
//                }
//                return Resource.Success(breakingNewsResponse ?: resultResponse)
//            }
//        }
//        return Resource.Error(response.message())
//    }
}