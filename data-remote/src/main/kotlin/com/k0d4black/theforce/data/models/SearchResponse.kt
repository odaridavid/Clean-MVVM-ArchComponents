package com.k0d4black.theforce.data.models


data class SearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<CharacterResponse>
)

