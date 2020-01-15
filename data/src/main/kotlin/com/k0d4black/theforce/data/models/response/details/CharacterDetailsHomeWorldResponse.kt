package com.k0d4black.theforce.data.models.response.details

import com.squareup.moshi.Json

data class CharacterDetailsHomeWorldResponse(@field:Json(name = "homeworld") val homeWorld: String)

/**
 * Object Representation of string response
 */
data class PlanetResponse(val name: String, val population: String)