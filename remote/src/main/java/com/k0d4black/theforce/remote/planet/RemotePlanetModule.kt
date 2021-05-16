package com.k0d4black.theforce.remote.planet

import com.k0d4black.theforce.remote.planet.data.PlanetApiService
import com.k0d4black.theforce.shared.planets.PlanetsDataSource
import com.k0d4black.theforce.remote.planet.data.PlanetsRemoteDataSource
import com.k0d4black.theforce.remote.planet.mappers.PlanetDetailsResponseMapper
import org.koin.dsl.module
import retrofit2.Retrofit

val remotePlanetModule = module {

    single {
        providePlanetApiService(retrofit = get())
    }

    single<PlanetsDataSource> {
        PlanetsRemoteDataSource(apiService = get(), planetDetailsResponseMapper = get())
    }

    single { PlanetDetailsResponseMapper() }
}

private fun providePlanetApiService(retrofit: Retrofit): PlanetApiService =
    retrofit.create(PlanetApiService::class.java)
