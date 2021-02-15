package com.k0d4black.theforce.feature.charactersearch.model

data class SearchViewState(
    val isRecentSearchLoading: Boolean = false,
    val recentSearches: List<RecentSearchPresentation>? = null
)
