package com.k0d4black.theforce.commons

internal object NavigationUtils {
    const val CHARACTER_PARCEL_KEY = "character_id"
}

enum class NavigationOrigin(name: String) {
    FAVORITES("favs"),
    SEARCH("search")
}