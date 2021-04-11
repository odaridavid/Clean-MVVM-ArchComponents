package com.k0d4black.theforce.remote.films.models

import com.squareup.moshi.Json

data class FilmUrlsResponse(@field:Json(name = "films") val filmUrls: List<String>)

data class FilmDetailsResponse(
    val title: String,
    @field:Json(name = "opening_crawl") val openingCrawl: String
)
