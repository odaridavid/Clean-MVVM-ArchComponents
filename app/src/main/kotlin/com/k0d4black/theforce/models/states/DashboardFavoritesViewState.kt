package com.k0d4black.theforce.models.states

import com.k0d4black.theforce.models.FavoritePresentation

internal data class DashboardFavoritesViewState(
    val isLoading: Boolean,
    val error: Error?,
    val favorites: List<FavoritePresentation>?
)