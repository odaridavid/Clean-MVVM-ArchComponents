package com.k0d4black.theforce.di

import com.k0d4black.theforce.viewmodel.CharacterDetailViewModel
import com.k0d4black.theforce.viewmodel.DashboardFavoritesViewModel
import com.k0d4black.theforce.viewmodel.DashboardSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        DashboardSearchViewModel(
            searchCharactersUseCase = get(named("search"))
        )
    }

    viewModel {
        CharacterDetailViewModel(
            getFilmsUseCase = get(named("films")),
            getPlanetUseCase = get(named("planet")),
            getSpeciesUseCase = get(named("species")),
            deleteFavoriteByNameUseCase = get(named("delete_favorite_by_name")),
            insertFavoriteUseCase = get(named("insert_favorite")),
            getFavoriteByNameUseCase = get(named("get_favorite_by_name"))
        )
    }

    viewModel {
        DashboardFavoritesViewModel(
            deleteAllFavoritesUseCase = get(named("delete_all_favorites")),
            deleteFavoriteByNameUseCase = get(named("delete_favorite_by_name")),
            getAllFavoritesUseCase = get(named("get_all_favorites"))
        )
    }

}