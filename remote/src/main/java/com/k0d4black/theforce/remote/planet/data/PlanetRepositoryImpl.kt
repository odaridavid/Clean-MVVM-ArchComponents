package com.k0d4black.theforce.remote.planet.data

import com.k0d4black.theforce.remote.isSuccessfulAndNotNull
import com.k0d4black.theforce.remote.planet.mappers.PlanetDetailsResponseMapper
import com.k0d4black.theforce.shared.enforceHttps
import com.k0d4black.theforce.shared.model.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlanetRepositoryImpl(
    private val apiService: PlanetApiService,
    private val planetDetailsResponseMapper: PlanetDetailsResponseMapper
) : PlanetRepository {

    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> = flow {
        val planetUrlResponse = apiService.getPlanetUrl(characterUrl = characterUrl.enforceHttps())
        if (!planetUrlResponse.isSuccessfulAndNotNull()) return@flow

        val planetDetailsResponse = apiService.getPlanetDetails(
            planetUrl = planetUrlResponse.body()!!.homeworldUrl.enforceHttps()
        )
        if (!planetDetailsResponse.isSuccessfulAndNotNull()) return@flow

        val planet = planetDetailsResponseMapper.mapToDomain(
            planetDetailsResponse = planetDetailsResponse.body()!!
        )
        emit(value = planet)
    }
}
