package com.k0d4black.theforce.remote.planet.data

import com.k0d4black.theforce.shared.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {

    suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet>
}