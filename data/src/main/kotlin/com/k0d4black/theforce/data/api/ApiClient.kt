package com.k0d4black.theforce.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private fun setupInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private fun setupOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(setupInterceptor())
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    /**
     * @param baseUrl
     * @param apiService
     *
     * @return T
     */
    internal fun <T> getInstance(baseUrl: String, apiService: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(setupOkhttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(apiService)
    }
}