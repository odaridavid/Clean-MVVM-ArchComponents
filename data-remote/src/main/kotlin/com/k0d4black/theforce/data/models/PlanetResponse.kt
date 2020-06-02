package com.k0d4black.theforce.data.models

import com.squareup.moshi.Json

data class PlanetResponse(@field:Json(name = "homeworld") val homeworldUrl: String)

data class PlanetDetailsResponse(val name: String, val population: String)