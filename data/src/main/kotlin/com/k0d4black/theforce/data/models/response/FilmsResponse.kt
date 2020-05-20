package com.k0d4black.theforce.data.models.response

import com.squareup.moshi.Json

data class FilmsResponse(@field:Json(name = "films")val filmUrls: List<String>)

data class FilmDetailResponse(
    val title:String,
    @field:Json(name = "opening_crawl") val openingCrawl: String
)