package com.k0d4black.theforce.remote.charactersearch.models

import com.squareup.moshi.Json

data class CharacterSearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<CharacterResponse>
)

data class CharacterResponse(
    val name: String,
    @field:Json(name = "birth_year") val birthYear: String,
    val height: String,
    val url: String
)
