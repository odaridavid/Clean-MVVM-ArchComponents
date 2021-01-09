package com.k0d4black.theforce.feature.character.details

val featureCharacterDetailsModule = {
    viewModel {
        com.k0d4black.theforce.feature.character.details.CharacterDetailViewModel(
            getFilmsUseCase = get(named("films")),
            getPlanetUseCase = get(named("planet")),
            getSpeciesUseCase = get(named("species"))
        )
    }



    viewModel {
        com.k0d4black.theforce.feature.favorites.FavoriteViewModel(
            deleteFavoriteByNameUseCase = get(named("delete_favorite_by_name")),
            insertFavoriteUseCase = get(named("insert_favorite")),
            getFavoriteByNameUseCase = get(named("get_favorite_by_name"))
        )
    }
}