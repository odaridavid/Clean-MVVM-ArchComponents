package com.k0d4black.theforce.feature.charactersearch.di

import com.k0d4black.theforce.feature.charactersearch.ui.CharacterSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureCharacterSearchModule = module {
    viewModel {
        CharacterSearchViewModel()
    }
}