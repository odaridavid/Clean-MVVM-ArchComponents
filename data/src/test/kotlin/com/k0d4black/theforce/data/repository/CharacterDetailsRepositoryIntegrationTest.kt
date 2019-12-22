package com.k0d4black.theforce.data.repository

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.helpers.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_CHARACTER_ID
import com.k0d4black.theforce.data.helpers.NON_EXISTANT_CHARACTER_ID
import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.Success
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterDetailsRepositoryIntegrationTest : BaseTest() {

    lateinit var characterDetailsRepository: CharacterDetailsRepository

    @Before
    override fun setup() {
        super.setup()
        val characterDetailsDataSourceMock =
            CharacterDetailsDataSource(
                starWarsApiService
            )
        characterDetailsRepository = CharacterDetailsRepository(characterDetailsDataSourceMock)
    }

    @Test
    fun `given a character id when executed then return character details`() {
        runBlocking {
            val characterDetailsDomainModel =
                characterDetailsRepository.getCharacterDetails(EXISTING_CHARACTER_ID)
            Truth.assertThat(characterDetailsDomainModel).isInstanceOf(Success::class.java)
        }
    }

    @Test
    fun `given an invalid character id when executed then return no results`() {
        runBlocking {
            val characterDetailsDomainModel =
                characterDetailsRepository.getCharacterDetails(NON_EXISTANT_CHARACTER_ID)
            Truth.assertThat(characterDetailsDomainModel).isInstanceOf(Error::class.java)
        }
    }

}