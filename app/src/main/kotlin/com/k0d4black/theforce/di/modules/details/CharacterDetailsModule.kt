package com.k0d4black.theforce.di.modules.details


import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.usecases.GetFilmsUseCase
import com.k0d4black.theforce.domain.usecases.GetPlanetUseCase
import com.k0d4black.theforce.domain.usecases.GetSpeciesUseCase
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsModule {

    @Provides
    fun provideCharacterFilmsUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetFilmsUseCase = GetFilmsUseCase(characterDetailsRepository)

    @Provides
    fun provideCharacterPlanetUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetPlanetUseCase = GetPlanetUseCase(characterDetailsRepository)

    @Provides
    fun provideCharacterSpeciesUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetSpeciesUseCase = GetSpeciesUseCase(characterDetailsRepository)

    @Provides
    fun provideCharacterDetailsDataSource(apiService: StarWarsApiService): CharacterDetailsDataSource =
        CharacterDetailsDataSource(apiService)

}