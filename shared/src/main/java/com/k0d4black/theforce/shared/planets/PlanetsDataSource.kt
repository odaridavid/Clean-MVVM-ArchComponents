package com.k0d4black.theforce.shared.planets

import kotlinx.coroutines.flow.Flow

interface PlanetsDataSource {

    suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet>
}
