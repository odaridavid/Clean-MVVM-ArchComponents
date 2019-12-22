package com.k0d4black.theforce.di.modules.details


import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.data.repository.CharacterSearchRepository
import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.data.source.CharacterSearchDataSource
import com.k0d4black.theforce.data.usecases.CharacterDetailsUseCase
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsModule {

    @Provides
    fun provideCharacteDetailsRepository(
        characterDetailsDataSource: CharacterDetailsDataSource
    ): CharacterDetailsRepository = CharacterDetailsRepository(characterDetailsDataSource)

    @Provides
    fun provideCharacterDetailsUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): CharacterDetailsUseCase = CharacterDetailsUseCase(characterDetailsRepository)

    @Provides
    fun provideCharacterDetailsDataSource(apiService: StarWarsApiService): CharacterDetailsDataSource =
        CharacterDetailsDataSource(apiService)

}