package com.k0d4black.theforce.di

import org.koin.dsl.module

val fakeNetworkModule = module {

    single { provideRetrofit(okHttpClient = get(), url = "http://localhost:8080/") }

    single { provideStarWarsService(retrofit = get()) }

    single { provideOkHttpClient() }
}
