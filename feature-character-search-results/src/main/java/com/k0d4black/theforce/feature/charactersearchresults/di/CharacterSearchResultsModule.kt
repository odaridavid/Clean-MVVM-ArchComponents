package com.k0d4black.theforce.feature.charactersearchresults.di

import com.k0d4black.theforce.feature.charactersearchresults.ui.CharacterSearchResultsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureCharacterSearchResultsModule = module {
    viewModel {
        CharacterSearchResultsViewModel(characterSearchRepository = get())
    }
}