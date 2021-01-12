package com.k0d4black.theforce.remote.planet

import org.koin.dsl.module
import retrofit2.Retrofit

val remotePlanetModule = module {

    single {
        providePlanetApiService(retrofit = get())
    }

    single<PlanetRepository> {
        PlanetRepositoryImpl(apiService = get(), planetDetailsResponseMapper = get())
    }

    single { PlanetDetailsResponseMapper() }
}

private fun providePlanetApiService(retrofit: Retrofit): PlanetApiService =
    retrofit.create(PlanetApiService::class.java)