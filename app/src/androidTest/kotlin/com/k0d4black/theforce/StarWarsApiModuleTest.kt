package com.k0d4black.theforce

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.di.modules.StarWarsApiModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit

class StarWarsApiModuleTest : StarWarsApiModule() {

    override fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return super.provideLoggingInterceptor()
    }

    override fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return super.provideOkHttpClient(httpLoggingInterceptor)
    }

    override fun provideStarWarsApi(retrofit: Retrofit): StarWarsApiService {
        return super.provideStarWarsApi(retrofit)
    }

    override fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return super.provideRetrofit(okHttpClient, baseUrl)
    }

    override fun provideBaseUrl(): String {
        return MockWebServer().url("/").toString()
    }
}