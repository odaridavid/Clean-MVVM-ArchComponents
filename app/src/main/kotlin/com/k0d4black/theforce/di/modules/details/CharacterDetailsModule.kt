package com.k0d4black.theforce.di.modules.details


import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.usecases.GetCharacterBasicInfoUseCase
import com.k0d4black.theforce.domain.usecases.GetCharacterFilmsUseCase
import com.k0d4black.theforce.domain.usecases.GetCharacterPlanetUseCase
import com.k0d4black.theforce.domain.usecases.GetCharacterSpeciesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsModule {

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