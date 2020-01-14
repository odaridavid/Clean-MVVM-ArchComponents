package com.k0d4black.theforce.di.modules.details


import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.repository.StarWarsCharacterDetailsRepository
import com.k0d4black.theforce.data.source.StarWarsCharacterDetailsDataSource
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterFilmsUseCase
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterPlanetUseCase
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterSpeciesUseCase
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsModule {

    @Provides
    fun provideCharacterFilmsUseCase(
        starWarsCharacterDetailsRepository: StarWarsCharacterDetailsRepository
    ): GetStarWarsCharacterFilmsUseCase = GetStarWarsCharacterFilmsUseCase(starWarsCharacterDetailsRepository)

    @Provides
    fun provideCharacterPlanetUseCase(
        starWarsCharacterDetailsRepository: StarWarsCharacterDetailsRepository
    ): GetStarWarsCharacterPlanetUseCase = GetStarWarsCharacterPlanetUseCase(starWarsCharacterDetailsRepository)

    @Provides
    fun provideCharacterSpeciesUseCase(
        starWarsCharacterDetailsRepository: StarWarsCharacterDetailsRepository
    ): GetStarWarsCharacterSpeciesUseCase = GetStarWarsCharacterSpeciesUseCase(starWarsCharacterDetailsRepository)

    @Provides
    fun provideCharacterDetailsDataSource(apiService: StarWarsApiService): StarWarsCharacterDetailsDataSource =
        StarWarsCharacterDetailsDataSource(apiService)

}