package com.k0d4black.theforce.shared.android

enum class AppScreen(val classPath: String) {
    ABOUT(
        classPath = "com.k0d4black.theforce.feature.about.ui.AboutActivity"
    ),
    SETTINGS(
        classPath = "com.k0d4black.theforce.feature.settings.ui.SettingsFragment"
    ),
    CHARACTER_SEARCH(
        classPath = "com.k0d4black.theforce.feature.charactersearch.ui.CharacterSearchFragment"
    ),
    CHARACTER_SEARCH_RESULTS(
        classPath = "com.k0d4black.theforce.feature.charactersearchresults.ui.CharacterSearchResultsActivity"
    ),
    CHARACTER_DETAILS(
        classPath = "com.k0d4black.theforce.feature.characterdetails.ui.CharacterDetailsActivity"
    ),
    RECENT_CHARACTER_SEARCH(
        classPath = "com.k0d4black.theforce.feature.recentcharactersearch.ui.RecentCharacterSearchFragment"
    ),
    HOME(
        classPath = "com.k0d4black.theforce.feature.home.ui.HomeActivity"
    ),
    FAVORITES(
        classPath = "com.k0d4black.theforce.feature.favoritecharacters.ui.FavoriteCharactersFragment"
    )
}