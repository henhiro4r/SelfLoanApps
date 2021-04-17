package com.example.selfloanapps.api

import com.example.selfloanapps.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor( Interceptor{ chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Accept", "application/json")
                    return@Interceptor chain.proceed(builder.build())
                })
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(Endpoints::class.java)
        }
    }
}