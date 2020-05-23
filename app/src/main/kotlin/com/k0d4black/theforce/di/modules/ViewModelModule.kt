package com.k0d4black.theforce.di.modules

import com.k0d4black.theforce.features.character_details.CharacterDetailViewModel
import com.k0d4black.theforce.features.character_search.CharacterSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { CharacterSearchViewModel(get()) }

    viewModel { CharacterDetailViewModel(get(), get(), get()) }
}