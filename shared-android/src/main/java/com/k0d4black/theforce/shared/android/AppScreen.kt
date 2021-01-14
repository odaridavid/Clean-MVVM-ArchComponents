package com.k0d4black.theforce.shared.android

enum class AppScreen(val className: String) {
    ABOUT(className = "AboutActivity"),
    SETTINGS(className = "SettingsActivity"),
    CHARACTER_SEARCH(className = "SearchActivity"),
    CHARACTER_DETAILS(className = "DetailsActivity"),
    HOME(className = "HomeActivity"),
    FAVORITES(className = "FavoritesActivity")
}