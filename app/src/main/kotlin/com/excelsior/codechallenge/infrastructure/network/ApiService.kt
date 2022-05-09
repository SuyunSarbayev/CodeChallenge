package com.excelsior.codechallenge.infrastructure.network

import com.excelsior.codechallenge.infrastructure.network.interceptors.HeadersInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiService {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val headersInterceptor = HeadersInterceptor()

    private val clientBuilder = OkHttpClient.Builder()
        .apply {
            addNetworkInterceptor(logging)
            addInterceptor(headersInterceptor)
            callTimeout(30000, TimeUnit.MILLISECONDS)
            connectTimeout(30000, TimeUnit.MILLISECONDS)
            readTimeout(30000, TimeUnit.MILLISECONDS)
        }

    private val apiService = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(clientBuilder.build())
        .build()
        .create(Api::class.java)

    fun get(): Api =
        apiService

    private companion object {

        const val API_BASE_URL = "https://technical-interview.excels.io/"
    }
}