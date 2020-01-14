package com.k0d4black.theforce.di.modules.search

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.repository.StarWarsCharacterSearchRepository
import com.k0d4black.theforce.data.source.StarWarsCharacterSearchDataSource
import com.k0d4black.theforce.domain.usecases.SearchStarWarsCharacterUseCase
import com.k0d4black.theforce.features.character_search.CharacterSearchViewModel
import com.k0d4black.theforce.features.character_search.SearchQueryListener
import dagger.Module
import dagger.Provides

@Module
class CharacterSearchModule {

    @Provides
    fun provideSearchQueryListener(characterSearchViewModel: CharacterSearchViewModel): SearchQueryListener {
        return SearchQueryListener(characterSearchViewModel)
    }

    @Provides
    fun provideCharacterSearchUseCase(
        starWarsCharacterSearchRepository: StarWarsCharacterSearchRepository
    ): SearchStarWarsCharacterUseCase = SearchStarWarsCharacterUseCase(starWarsCharacterSearchRepository)

    @Provides
    fun provideCharacterSearchDataSource(apiService: StarWarsApiService): StarWarsCharacterSearchDataSource =
        StarWarsCharacterSearchDataSource(apiService)

}


