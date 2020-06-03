package com.k0d4black.theforce.di

import com.k0d4black.theforce.viewmodel.CharacterDetailViewModel
import com.k0d4black.theforce.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        DashboardViewModel(
            searchCharactersUseCase = get(named("search")),
            deleteAllFavoritesBaseUseCase = get(named("delete_all_favorite")),
            deleteFavoriteBaseUseCase = get(named("delete_favorite")),
            getAllFavoritesBaseUseCase = get(named("get_all_favorites"))
        )
    }

    viewModel {
        CharacterDetailViewModel(
            getFilmsUseCase = get(named("films")),
            getPlanetUseCase = get(named("planet")),
            getSpeciesUseCase = get(named("species")),
            deleteFavoriteBaseUseCase = get(named("delete_favorite")),
            insertFavoriteBaseUseCase = get(named("insert_favorite")),
            getFavoriteByNameBaseUseCase = get(named("get_favorite_by_name")),
            getFavoriteByIdBaseUseCase = get(named("get_favorite_by_id"))
        )
    }
}