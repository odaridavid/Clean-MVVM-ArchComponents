package com.k0d4black.theforce.data.repository

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.helpers.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_SEARCH_PARAMS
import com.k0d4black.theforce.data.helpers.NON_EXISTENT_SEARCH_PARAMS
import com.k0d4black.theforce.data.source.search.CharacterSearchDataSourceImpl
import com.k0d4black.theforce.domain.models.CharacterDetailsDomainModel
import com.k0d4black.theforce.domain.repository.CharacterSearchRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterSearchRepositoryImplIntegrationTest : BaseTest() {

    lateinit var characterSearchRepository: CharacterSearchRepository

    @Before
    override fun setup() {
        super.setup()
        val characterSearchDataSourceMock = CharacterSearchDataSourceImpl(starWarsApiService)

        characterSearchRepository = CharacterSearchRepositoryImpl(characterSearchDataSourceMock)
    }

    @Test
    fun `given existing search parameters when executed then return list of search domain elements`() {
        runBlocking {
            val results = characterSearchRepository.searchCharacters(EXISTING_SEARCH_PARAMS)
            Truth.assertThat(results).isNotEmpty()
            Truth.assertThat(results).isInstanceOf(ArrayList<CharacterDetailsDomainModel>()::class.java)
        }
    }

    @Test
    fun `given non-existing search parameters when executed then return no results`() {
        runBlocking {
            val results = characterSearchRepository.searchCharacters(NON_EXISTENT_SEARCH_PARAMS)
            Truth.assertThat(results).isEmpty()
        }
    }

}