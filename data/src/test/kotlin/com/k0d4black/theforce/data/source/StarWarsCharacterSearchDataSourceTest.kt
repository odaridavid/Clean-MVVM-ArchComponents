package com.k0d4black.theforce.data.source

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_SEARCH_PARAMS
import com.k0d4black.theforce.data.helpers.NON_EXISTENT_SEARCH_PARAMS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class StarWarsCharacterSearchDataSourceTest : BaseTest() {

    private lateinit var starWarsCharacterSearchDataSource: StarWarsCharacterSearchDataSource

    @Before
    override fun setup() {
        super.setup()
        starWarsCharacterSearchDataSource = StarWarsCharacterSearchDataSource(starWarsApiService)
    }

    @Test
    fun `given search parameters when parameters exist then get available matching characters`() {
        runBlocking {
            val response = starWarsCharacterSearchDataSource.query(EXISTING_SEARCH_PARAMS)
            response.collect { Truth.assertThat(it).isNotEmpty() }
        }
    }

    @Test
    fun `given search parameters when parameters dont exist then get no matching characters`() {
        runBlocking {
            val response = starWarsCharacterSearchDataSource.query(NON_EXISTENT_SEARCH_PARAMS)
            response.collect { Truth.assertThat(it).isEmpty() }
        }
    }
}