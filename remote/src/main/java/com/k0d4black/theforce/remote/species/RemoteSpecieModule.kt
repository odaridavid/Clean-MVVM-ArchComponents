package com.k0d4black.theforce.remote.species

import com.k0d4black.theforce.remote.species.data.SpecieRepository
import com.k0d4black.theforce.remote.species.data.SpecieRepositoryImpl
import com.k0d4black.theforce.remote.species.data.SpeciesApiService
import com.k0d4black.theforce.remote.species.mappers.SpecieDetailsResponseMapper
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
