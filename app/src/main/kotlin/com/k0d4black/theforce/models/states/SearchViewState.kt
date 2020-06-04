package com.k0d4black.theforce.models.states

import com.k0d4black.theforce.models.CharacterPresentation

internal data class SearchViewState(
    val isLoading: Boolean,
    val error: Error?,
    val searchResults: List<CharacterPresentation>?
)