package com.k0d4black.theforce.data.source

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_CHARACTER_URL
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterDetailsRemoteDataSourceTest : BaseTest() {

    private lateinit var characterDetailsRemoteDataSource: CharacterDetailsRemoteDataSource

    @Before
    override fun setup() {
        super.setup()
        characterDetailsRemoteDataSource = CharacterDetailsRemoteDataSource(starWarsApiService)
    }

    @Test
    fun `given a valid character id when executed then return character details`() {
        runBlocking {
            val filmsFlow =
                characterDetailsRemoteDataSource.getCharacterFilms(EXISTING_CHARACTER_URL)
            val speciesFlow =
                characterDetailsRemoteDataSource.getCharacterSpecies(EXISTING_CHARACTER_URL)
            val planetFlow =
                characterDetailsRemoteDataSource.getCharacterPlanet(EXISTING_CHARACTER_URL)

            filmsFlow.collect { films ->
                Truth.assertThat(films.size).isAtLeast(1)
            }
            planetFlow.collect { planet ->
                Truth.assertThat(planet.name).matches("Tatooine")
            }
            speciesFlow.collect { species ->
                Truth.assertThat(species.size).isAtLeast(1)
            }
        }
    }

}