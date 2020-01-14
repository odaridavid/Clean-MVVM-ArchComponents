package com.k0d4black.theforce.data.models.response.details

import com.squareup.moshi.Json


data class CharacterDetailsFilmsResponse(val films: List<String>)

/**
 * Object Representation of string response
 */
data class FilmResponse(
    val title:String,
    @field:Json(name = "opening_crawl") val openingCrawl: String
)