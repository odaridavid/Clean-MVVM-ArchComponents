package com.k0d4black.theforce.remote.species.data

import com.k0d4black.theforce.remote.core.isSuccessfulAndNotNull
import com.k0d4black.theforce.remote.species.mappers.SpecieDetailsResponseMapper
import com.k0d4black.theforce.shared.extensions.enforceHttps
import com.k0d4black.theforce.shared.species.Specie
import com.k0d4black.theforce.shared.species.SpeciesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpeciesRemoteDataSource(
    private val apiService: SpeciesApiService,
    private val specieDetailsResponseMapper: SpecieDetailsResponseMapper
) : SpeciesDataSource {


    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> = flow {
        val specieUrlsResponse = apiService.getSpecieUrls(
            characterUrl = characterUrl.enforceHttps()
        )
        if (!specieUrlsResponse.isSuccessfulAndNotNull()) return@flow

        val species = mutableListOf<Specie>()
        specieUrlsResponse.body()?.run {
            for (specieUrl in speciesUrls) {
                val specieDetailsResponse = apiService.getSpecieDetails(
                    specieUrl = specieUrl.enforceHttps()
                )
                if (!specieDetailsResponse.isSuccessfulAndNotNull()) return@run
                val specie = specieDetailsResponseMapper.mapToDomain(
                    specieDetailsResponse = specieDetailsResponse.body()!!
                )
                species.add(specie)
            }
        }
        emit(value = species)
    }
}
