package com.k0d4black.theforce.shared.species

import kotlinx.coroutines.flow.Flow

interface SpeciesDataSource {

    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>>
}
