package com.k0d4black.theforce.di

import com.k0d4black.theforce.features.character_details.CharacterDetailViewModel
import com.k0d4black.theforce.features.character_search.CharacterSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { CharacterSearchViewModel(searchCharactersUseCase = get(named("search"))) }

    viewModel {
        CharacterDetailViewModel(
            getFilmsUseCase = get(named("films")),
            getPlanetUseCase = get(named("planet")),
            getSpeciesUseCase = get(named("species"))
        )
    }
}