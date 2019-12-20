package com.k0d4black.theforce.data.models.response

import com.squareup.moshi.Json

internal data class CharacterDetailsResponse(
    val name: String,
    @field:Json(name = "birth_year") val birthYear: String,
    val height: String,
    val species: List<String>,
    val films: List<String>,
    @field:Json(name = "homeworld") val homeWorld: String
)