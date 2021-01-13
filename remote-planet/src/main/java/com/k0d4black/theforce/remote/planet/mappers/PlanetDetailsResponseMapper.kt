package com.k0d4black.theforce.remote.planet.mappers

import com.k0d4black.theforce.remote.planet.models.PlanetDetailsResponse
import com.k0d4black.theforce.shared.model.Planet

class PlanetDetailsResponseMapper {

    fun toDomain(planetDetailsResponse: PlanetDetailsResponse): Planet =
        with(planetDetailsResponse) { Planet(name = name, population = population) }

}