package com.k0d4black.theforce.data.repository

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_SEARCH_PARAMS
import com.k0d4black.theforce.data.helpers.NON_EXISTENT_SEARCH_PARAMS
import com.k0d4black.theforce.data.source.CharacterSearchDataSource
import com.k0d4black.theforce.domain.utils.Success
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterSearchRepositoryIntegrationTest : BaseTest() {

    lateinit var characterSearchRepository: CharacterSearchRepository

    @Before
    override fun setup() {
        super.setup()
        val characterSearchDataSourceMock =
            CharacterSearchDataSource(
                starWarsApiService
            )

        characterSearchRepository = CharacterSearchRepository(characterSearchDataSourceMock)
    }

    @Test
    fun `given existing search parameters when executed then return list of search results`() {
        runBlocking {
            val results = characterSearchRepository.searchCharacters(EXISTING_SEARCH_PARAMS)
            Truth.assertThat(results).isInstanceOf(Success::class.java)
        }
    }

    @Test
    fun `given non-existing search parameters when executed then return no results`() {
        runBlocking {
            val results = characterSearchRepository.searchCharacters(NON_EXISTENT_SEARCH_PARAMS)
            Truth.assertThat(results).isInstanceOf(Success::class.java)
        }
    }

}