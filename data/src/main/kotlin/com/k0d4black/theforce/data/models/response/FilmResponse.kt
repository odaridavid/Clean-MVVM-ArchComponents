package com.k0d4black.theforce.data.models.response

import com.squareup.moshi.Json

data class FilmResponse(
    val title:String,
    @field:Json(name = "opening_crawl") val openingCrawl: String
)