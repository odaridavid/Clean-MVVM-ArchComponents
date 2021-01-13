package com.k0d4black.theforce.remote.species.data

import com.k0d4black.theforce.remote.core.isSuccessfulAndNotNull
import com.k0d4black.theforce.remote.species.mappers.SpecieDetailsResponseMapper
import com.k0d4black.theforce.shared.enforceHttps
import com.k0d4black.theforce.shared.model.Specie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpecieRepositoryImpl(
    private val apiService: SpeciesApiService,
    private val specieDetailsResponseMapper: SpecieDetailsResponseMapper
) : SpecieRepository {


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
                val specie = specieDetailsResponseMapper.toDomain(
                    specieDetailsResponse = specieDetailsResponse.body()!!
                )
                species.add(specie)
            }
        }
        emit(value = species)
    }
}