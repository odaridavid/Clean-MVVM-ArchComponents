package com.k0d4black.theforce.feature.recentcharactersearch.di

import com.k0d4black.theforce.feature.recentcharactersearch.ui.RecentCharacterSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureRecentCharacterSearchModule = module {
    viewModel {
        RecentCharacterSearchViewModel()
    }
}