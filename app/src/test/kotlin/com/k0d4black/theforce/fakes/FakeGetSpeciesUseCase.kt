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

import com.k0d4black.theforce.domain.models.Specie
import com.k0d4black.theforce.domain.usecases.SpeciesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeGetSpeciesUseCase : SpeciesUseCase {
    override suspend fun invoke(params: String): Flow<List<Specie>> = flow {
        emit(
            listOf(
                Specie(name = "name", language = "language")
            )
        )
    }

}