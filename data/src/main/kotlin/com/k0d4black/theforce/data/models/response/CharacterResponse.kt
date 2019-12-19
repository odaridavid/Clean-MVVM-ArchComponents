package com.k0d4black.theforce.data.models.response

internal data class CharacterResponse(
    val name: String,
    val species: List<String>,
    val url: String
)