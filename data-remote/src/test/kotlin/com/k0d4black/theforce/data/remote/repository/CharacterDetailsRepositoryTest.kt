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
import com.k0d4black.theforce.data.remote.helpers.Constants.EXISTING_CHARACTER_URL
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterDetailsRepositoryTest : BaseTest() {

    private lateinit var characterDetailsRepository: ICharacterDetailsRepository

    @Before
    override fun setup() {
        super.setup()
        characterDetailsRepository = CharacterDetailsRepository(starWarsApiService)
    }

    @Test
    fun `given a character id when executed then return character details`() {
        runBlocking {
            val filmsFlow = characterDetailsRepository.getCharacterFilms(EXISTING_CHARACTER_URL)
            val speciesFlow = characterDetailsRepository.getCharacterSpecies(EXISTING_CHARACTER_URL)
            val planetFlow = characterDetailsRepository.getCharacterPlanet(EXISTING_CHARACTER_URL)

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