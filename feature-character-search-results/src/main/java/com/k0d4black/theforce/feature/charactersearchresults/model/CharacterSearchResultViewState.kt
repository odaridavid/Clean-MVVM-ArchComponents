package com.k0d4black.theforce.feature.charactersearchresults.model

data class CharacterSearchResultViewState(
    val characterSearchResultSearchResults: List<CharacterSearchResultPresentation>? = null,
    val isLoading: Boolean = false
)
