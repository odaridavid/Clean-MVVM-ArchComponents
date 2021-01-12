package com.k0d4black.theforce.remote.planet

import com.squareup.moshi.Json

data class PlanetUrlResponse(@field:Json(name = "homeworld") val homeworldUrl: String)

data class PlanetDetailsResponse(val name: String, val population: String)
