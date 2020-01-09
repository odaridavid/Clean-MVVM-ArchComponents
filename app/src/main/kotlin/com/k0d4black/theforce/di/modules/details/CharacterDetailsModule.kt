package com.k0d4black.theforce.di.modules.details


import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.data.usecases.GetCharacterBasicInfoUseCase
import com.k0d4black.theforce.data.usecases.GetCharacterFilmsUseCase
import com.k0d4black.theforce.data.usecases.GetCharacterPlanetUseCase
import com.k0d4black.theforce.data.usecases.GetCharacterSpeciesUseCase
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsModule {

    @Provides
    fun provideCharacteDetailsRepository(
        characterDetailsDataSource: CharacterDetailsDataSource
    ): CharacterDetailsRepository = CharacterDetailsRepository(characterDetailsDataSource)

    @Provides
    fun provideCharacterBasicInfoUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetCharacterBasicInfoUseCase = GetCharacterBasicInfoUseCase(characterDetailsRepository)

    @Provides
    fun provideCharacterFilmsUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetCharacterFilmsUseCase = GetCharacterFilmsUseCase(characterDetailsRepository)

    @Provides
    fun provideCharacterPlanetUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetCharacterPlanetUseCase = GetCharacterPlanetUseCase(characterDetailsRepository)

    @Provides
    fun provideCharacterSpeciesUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetCharacterSpeciesUseCase = GetCharacterSpeciesUseCase(characterDetailsRepository)

    @Provides
    fun provideCharacterDetailsDataSource(apiService: StarWarsApiService): CharacterDetailsDataSource =
        CharacterDetailsDataSource(apiService)

}