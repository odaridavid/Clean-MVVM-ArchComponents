package com.k0d4black.theforce.remote.species

import org.koin.dsl.module
import retrofit2.Retrofit

val remoteSpeciesModule = module {

    single {
        provideSpeciesApiService(retrofit = get())
    }

    single<SpecieRepository> {
        SpecieRepositoryImpl(apiService = get(), specieDetailsResponseMapper = get())
    }

    single { SpecieDetailsResponseMapper() }
}

private fun provideSpeciesApiService(retrofit: Retrofit): SpeciesApiService =
    retrofit.create(SpeciesApiService::class.java)