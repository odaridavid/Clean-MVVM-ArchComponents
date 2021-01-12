package com.k0d4black.theforce.remote.species

import com.k0d4black.theforce.shared.model.Specie
import kotlinx.coroutines.flow.Flow

interface SpecieRepository {

    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>>
}
