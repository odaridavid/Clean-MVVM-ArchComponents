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

import com.k0d4black.theforce.domain.models.Favorite
import com.k0d4black.theforce.domain.usecases.InsertFavoriteBaseUseCase
import com.k0d4black.theforce.utils.Data
import com.k0d4black.theforce.utils.UiState
import kotlinx.coroutines.flow.Flow


class FakeInsertFavoriteUseCase(
    uiState: UiState
) : BaseTestUseCase<String, Favorite>(uiState), InsertFavoriteBaseUseCase {

    override suspend fun invoke(params: Favorite): Flow<String> {
        Data.favorites.add(params)
        return execute(params)
    }

    override fun getValue(params: Favorite): String {
        return if (Data.favorites.size == 1) "Done" else "Failed"
    }

}

