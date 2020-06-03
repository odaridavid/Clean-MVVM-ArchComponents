/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *            http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.k0d4black.theforce.fakes

import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.usecases.SearchCharactersBaseUseCase
import com.k0d4black.theforce.utils.UiState
import kotlinx.coroutines.flow.Flow

class FakeSearchCharactersUseCase(
    uiState: UiState
) : BaseTestUseCase<List<Character>>(uiState), SearchCharactersBaseUseCase {

    override suspend fun invoke(params: String): Flow<List<Character>> = execute()

    override fun getValue(): List<Character> =
        listOf(
            Character(
                "Darth Vader",
                "12BBY",
                "123",
                "https://swapi.co/api/species/2/"
            )
        )

}