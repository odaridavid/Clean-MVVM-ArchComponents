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
package com.k0d4black.theforce.di

import com.k0d4black.theforce.viewmodel.CharacterDetailViewModel
import com.k0d4black.theforce.viewmodel.DashboardFavoritesViewModel
import com.k0d4black.theforce.viewmodel.DashboardSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        DashboardSearchViewModel(
            searchCharactersUseCase = get(named("search"))
        )
    }

    viewModel {
        CharacterDetailViewModel(
            getFilmsUseCase = get(named("films")),
            getPlanetUseCase = get(named("planet")),
            getSpeciesUseCase = get(named("species")),
            deleteFavoriteByNameUseCase = get(named("delete_favorite_by_name")),
            insertFavoriteUseCase = get(named("insert_favorite")),
            getFavoriteByNameUseCase = get(named("get_favorite_by_name"))
        )
    }

    viewModel {
        DashboardFavoritesViewModel(
            deleteAllFavoritesUseCase = get(named("delete_all_favorites")),
            deleteFavoriteByNameUseCase = get(named("delete_favorite_by_name")),
            getAllFavoritesUseCase = get(named("get_all_favorites"))
        )
    }

}