package com.k0d4black.theforce.data.models.response.search

import com.k0d4black.theforce.data.models.response.CharacterResponse


data class CharacterSearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<CharacterResponse>
)

