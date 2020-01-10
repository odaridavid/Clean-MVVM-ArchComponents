package com.k0d4black.theforce.data.repository

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_CHARACTER_ID
import com.k0d4black.theforce.data.helpers.NON_EXISTANT_CHARACTER_ID
import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

internal class CharacterDetailsRepositoryIntegrationTest : BaseTest() {

    private lateinit var characterDetailsRepository: ICharacterDetailsRepository

    @Before
    override fun setup() {
        super.setup()
        val characterDetailsDataSourceMock = CharacterDetailsDataSource(starWarsApiService)
        characterDetailsRepository = CharacterDetailsRepository(characterDetailsDataSourceMock)
    }

    @Test
    fun `given a character id when executed then return character details`() {
        runBlocking {
            val characterDetailsFlow =
                characterDetailsRepository.getCharacterDetails(EXISTING_CHARACTER_ID)
            val characterFilmsFlow =
                characterDetailsRepository.getCharacterFilms(EXISTING_CHARACTER_ID)
            val characterSpeciesFlow =
                characterDetailsRepository.getCharacterSpecies(EXISTING_CHARACTER_ID)
            val characterPlanetFlow =
                characterDetailsRepository.getCharacterPlanet(EXISTING_CHARACTER_ID)

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
    fun `given an invalid character id when executed then return no results`() {
        runBlocking { characterDetailsRepository.getCharacterDetails(NON_EXISTANT_CHARACTER_ID) }
    }

}