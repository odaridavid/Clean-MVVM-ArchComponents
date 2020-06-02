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
package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.models.Favorite
import com.k0d4black.theforce.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow

typealias GetFavoriteByNameBaseUseCase = BaseUseCase<String, Flow<Favorite>>

class GetFavoriteByNameUseCase(
    private val favoritesRepository: IFavoritesRepository
) : GetFavoriteByNameBaseUseCase {

    override suspend fun invoke(params: String): Flow<Favorite> =
        favoritesRepository.getFavoriteByName(params)

}