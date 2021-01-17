package com.k0d4black.theforce.shared.android

enum class AppScreen(val classPath: String) {
    ABOUT(classPath = "com.k0d4black.theforce.feature.about.AboutActivity"),
    SETTINGS(
        classPath = "com.k0d4black.theforce.feature.settings.SettingsFragment"
    ),
    CHARACTER_SEARCH(
        classPath = "com.k0d4black.theforce.feature.charactersearch.ui.CharacterSearchFragment"
    ),
    CHARACTER_DETAILS(
        classPath = "com.k0d4black.theforce.feature.characterdetails.ui.CharacterDetailsActivity"
    ),
    HOME(classPath = "com.k0d4black.theforce.feature.home.ui.HomeActivity"),
    FAVORITES(
        classPath = "com.k0d4black.theforce.feature.favoritecharacters.ui.FavoriteCharactersFragment"
    )
}