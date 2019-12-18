package com.k0d4black.theforce.data.repository

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.helpers.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_CHARACTER_ID
import com.k0d4black.theforce.data.helpers.NON_EXISTANT_CHARACTER_ID
import com.k0d4black.theforce.data.source.details.CharacterDetailsDataSourceImpl
import com.k0d4black.theforce.domain.repository.CharacterDetailsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterDetailsRepositoryImplIntegrationTest : BaseTest() {

    lateinit var characterDetailsRepository: CharacterDetailsRepository

    @Before
    override fun setup() {
        super.setup()
        val characterDetailsDataSourceMock = CharacterDetailsDataSourceImpl(starWarsApiService)
        characterDetailsRepository = CharacterDetailsRepositoryImpl(characterDetailsDataSourceMock)
    }

    @Test
    fun `given character of interest id  when executed then return character details`() {
        runBlocking {
            val characterDetailsDomainModel = characterDetailsRepository.getCharacterDetails(EXISTING_CHARACTER_ID)
            Truth.assertThat(characterDetailsDomainModel?.films).isNotEmpty()
            Truth.assertThat(characterDetailsDomainModel?.species).isNotEmpty()
            Truth.assertThat(characterDetailsDomainModel?.name).matches("Luke Skywalker")
        }
    }

    @Test
    fun `given invalid character id when executed then return no results`() {
        runBlocking {
            val characterDetailsDomainModel = characterDetailsRepository.getCharacterDetails(NON_EXISTANT_CHARACTER_ID)
            Truth.assertThat(characterDetailsDomainModel).isNull()
        }
    }

}