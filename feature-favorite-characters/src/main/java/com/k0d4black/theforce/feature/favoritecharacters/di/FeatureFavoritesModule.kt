package com.k0d4black.theforce.feature.favoritecharacters.di

import com.k0d4black.theforce.feature.favoritecharacters.ui.FavoriteCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureFavoritesModule = module {
    viewModel {
        FavoriteCharactersViewModel(favoriteCharactersRepository = get())
    }
}