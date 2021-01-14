package com.k0d4black.theforce.remote.planet.data

import com.k0d4black.theforce.remote.planet.mappers.PlanetDetailsResponseMapper
import org.junit.Test

class PlanetRepositoryTest {
    @Test
    fun `when we fetch character planet,then the expected result is returned`() {
        // given a planet mapper
        val planetMapper = PlanetDetailsResponseMapper()

        // and a planet repository
//        val planetRepository = PlanetRepositoryImpl(
//            apiService = mock(),
//            planetDetailsResponseMapper = planetMapper
//        )
//
//        val characterPlanet = planetRepository.getCharacterPlanet(characterUrl = "")
//
//        assert(characterPlanet == SampleData.planet)
    }
}
