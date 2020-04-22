package com.k0d4black.theforce.data.source

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_CHARACTER_URL
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class StarWarsCharacterDetailsDataSourceTest : BaseTest() {

    private lateinit var starWarsCharacterDetailsDataSource: StarWarsCharacterDetailsDataSource

    @Before
    override fun setup() {
        super.setup()
        starWarsCharacterDetailsDataSource = StarWarsCharacterDetailsDataSource(starWarsApiService)
    }

    @Test
    fun `given a valid character id when executed then return character details`() {
        runBlocking {
            val characterFilmsFlow =
                starWarsCharacterDetailsDataSource.getCharacterFilms(EXISTING_CHARACTER_URL)
            val characterSpeciesFlow =
                starWarsCharacterDetailsDataSource.getCharacterSpecies(EXISTING_CHARACTER_URL)
            val characterPlanetFlow =
                starWarsCharacterDetailsDataSource.getCharacterPlanet(EXISTING_CHARACTER_URL)

            characterFilmsFlow.collect {
                Truth.assertThat(it.size).isAtLeast(1)
            }
            characterPlanetFlow.collect {
                Truth.assertThat(it.name).matches("Tatooine")
            }
            characterSpeciesFlow.collect {
                Truth.assertThat(it.size).isAtLeast(1)
            }
        }
    }

}