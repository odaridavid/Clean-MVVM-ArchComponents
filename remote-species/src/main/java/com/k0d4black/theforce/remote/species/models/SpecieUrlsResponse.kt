package com.k0d4black.theforce.remote.species.models

import com.squareup.moshi.Json

data class SpecieUrlsResponse(@field:Json(name = "species") val speciesUrls: List<String>)

data class SpecieDetailsResponse(val name: String, val language: String)
