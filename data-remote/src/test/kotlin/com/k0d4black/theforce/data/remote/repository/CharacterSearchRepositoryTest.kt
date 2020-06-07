/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.k0d4black.theforce.data.remote.repository

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.remote.BaseTest
import com.k0d4black.theforce.data.remote.helpers.Constants.EXISTING_SEARCH_PARAMS
import com.k0d4black.theforce.data.remote.helpers.Constants.NON_EXISTENT_SEARCH_PARAMS
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterSearchRepositoryTest : BaseTest() {

    private lateinit var characterSearchRepository: ICharacterSearchRepository

    @Before
    override fun setup() {
        super.setup()
        characterSearchRepository = CharacterSearchRepository(starWarsApiService)
    }

    @Test
    fun `given existing search parameters when executed then return list of search results`() {
        runBlocking {
            val results = characterSearchRepository.searchCharacters(EXISTING_SEARCH_PARAMS)
            results.collect { Truth.assertThat(it).isNotEmpty() }
        }
    }

    @Test
    fun `given non-existing search parameters when executed then return no results`() {
        runBlocking {
            val results = characterSearchRepository.searchCharacters(NON_EXISTENT_SEARCH_PARAMS)
            results.collect { Truth.assertThat(it).isEmpty() }
        }
    }

}