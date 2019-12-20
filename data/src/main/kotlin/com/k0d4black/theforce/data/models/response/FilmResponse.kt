package com.k0d4black.theforce.data.models.response

import com.squareup.moshi.Json

internal data class FilmResponse(@field:Json(name="opening_crawl")val openingCrawl: String)