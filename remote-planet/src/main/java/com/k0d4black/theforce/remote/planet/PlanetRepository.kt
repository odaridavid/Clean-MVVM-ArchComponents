package com.k0d4black.theforce.remote.planet

import com.k0d4black.theforce.shared.model.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {

    suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet>
}