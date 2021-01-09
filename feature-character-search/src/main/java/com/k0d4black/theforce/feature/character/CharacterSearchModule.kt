package com.k0d4black.theforce.feature.character

val featureCharacterDetailsModule = {
    viewModel {
        DashboardSearchViewModel(
            searchCharactersUseCase = get(named("search"))
        )
    }
}