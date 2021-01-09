package com.k0d4black.theforce.feature.favorites

val featureCharacterDetailsModule = {
    viewModel {
        com.k0d4black.theforce.feature.favorites.DashboardFavoritesViewModel(
            deleteAllFavoritesUseCase = get(named("delete_all_favorites")),
            deleteFavoriteByNameUseCase = get(named("delete_favorite_by_name")),
            getAllFavoritesUseCase = get(named("get_all_favorites"))
        )
    }
}