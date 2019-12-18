package com.k0d4black.theforce.data.source

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.helpers.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_SEARCH_PARAMS
import com.k0d4black.theforce.data.helpers.NON_EXISTENT_SEARCH_PARAMS
import com.k0d4black.theforce.data.source.search.CharacterSearchDataSource
import com.k0d4black.theforce.data.source.search.CharacterSearchDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class CharacterSearchDataSourceTest : BaseTest() {
    lateinit var characterSearchDataSource: CharacterSearchDataSource

    @Before
    override fun setup() {
        super.setup()
        characterSearchDataSource = CharacterSearchDataSourceImpl(starWarsApiService)
    }

    @Test
    fun `given existing parameters when character search is executed then get available matching characters`() {
        runBlocking {
            val response = characterSearchDataSource.query(EXISTING_SEARCH_PARAMS)
            Truth.assertThat(response).isNotEmpty()
        }
    }

    @Test
    fun `given non-existing parameters when character search executed then get no matching characters`() {
        runBlocking {
            val response = characterSearchDataSource.query(NON_EXISTENT_SEARCH_PARAMS)
            Truth.assertThat(response).isEmpty()
        }

    }
}