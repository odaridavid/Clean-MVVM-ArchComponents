package com.k0d4black.theforce.di.modules

import com.k0d4black.theforce.data.api.HttpClient
import com.k0d4black.theforce.data.api.LoggingInterceptor
import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.api.ApiUtils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
open class StarWarsApiModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = LoggingInterceptor.create()

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkhttpClient(httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    fun provideStarWarsApi(retrofit: Retrofit): StarWarsApiService {
        return retrofit.create(StarWarsApiService::class.java)
    }

    @Singleton
    @Provides
    open fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiUtils.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}