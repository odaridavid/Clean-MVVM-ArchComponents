package com.k0d4black.theforce.data.source

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_CHARACTER_ID
import com.k0d4black.theforce.data.helpers.NON_EXISTANT_CHARACTER_ID
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

internal class CharacterDetailsDataSourceTest : BaseTest() {

    private lateinit var characterDetailsDataSource: CharacterDetailsDataSource

    @Before
    override fun setup() {
        super.setup()
        characterDetailsDataSource = CharacterDetailsDataSource(starWarsApiService)
    }

    @Test
    fun `given a valid character id when executed then return character details`() {
        runBlocking {
            val characterDetailsFlow =
                characterDetailsDataSource.getCharacterDetails(EXISTING_CHARACTER_ID)
            val characterFilmsFlow =
                characterDetailsDataSource.getCharacterFilms(EXISTING_CHARACTER_ID)
            val characterSpeciesFlow =
                characterDetailsDataSource.getCharacterSpecies(EXISTING_CHARACTER_ID)
            val characterPlanetFlow =
                characterDetailsDataSource.getCharacterPlanet(EXISTING_CHARACTER_ID)

            characterDetailsFlow.collect {
                Truth.assertThat(it.name).matches("Luke Skywalker")
            }
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

    @Test(expected = HttpException::class)
    fun `given invalid character id when executed then return error response `() {
        runBlocking {
            characterDetailsDataSource.getCharacterDetails(NON_EXISTANT_CHARACTER_ID)
        }
    }
}