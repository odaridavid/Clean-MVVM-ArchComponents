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
package com.k0d4black.theforce.di

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import com.k0d4black.theforce.domain.repository.IFavoritesRepository
import com.k0d4black.theforce.domain.usecases.*
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCasesModule = module {

    single(named("search")) { provideSearchUseCase(get()) }

    single(named("species")) { provideSpeciesUseCase(get()) }

    single(named("planet")) { providePlanetUseCase(get()) }

    single(named("films")) { provideFilmsUseCase(get()) }

    single(named("delete_favorite_by_id")) { provideDeleteFavoriteByIdUseCase(get()) }

    single(named("delete_favorite_by_name")) { provideDeleteFavoriteByNameUseCase(get()) }

    single(named("delete_all_favorites")) { provideDeleteAllFavoritesUseCase(get()) }

    single(named("get_favorite_by_name")) { provideGetFavoriteByNameUseCase(get()) }

    single(named("get_favorite_by_id")) { provideGetFavoriteByIdUseCase(get()) }

    single(named("get_all_favorites")) { provideGetAllFavoritesUseCase(get()) }

    single(named("insert_favorite")) { provideInsertFavoriteUseCase(get()) }

}

fun provideSearchUseCase(searchRepository: ICharacterSearchRepository): SearchCharactersBaseUseCase {
    return SearchCharactersUseCase(searchRepository)
}

fun provideSpeciesUseCase(detailsRepository: ICharacterDetailsRepository): GetSpeciesBaseUseCase {
    return GetSpeciesUseCase(detailsRepository)
}

fun providePlanetUseCase(detailsRepository: ICharacterDetailsRepository): GetPlanetBaseUseCase {
    return GetPlanetUseCase(detailsRepository)
}

fun provideFilmsUseCase(detailsRepository: ICharacterDetailsRepository): GetFilmsBaseUseCase {
    return GetFilmsUseCase(detailsRepository)
}

fun provideDeleteFavoriteByIdUseCase(favoritesRepository: IFavoritesRepository): DeleteFavoriteByIdBaseUseCase {
    return DeleteFavoriteByIdUseCase(favoritesRepository)
}

fun provideDeleteFavoriteByNameUseCase(favoritesRepository: IFavoritesRepository): DeleteFavoriteByNameBaseUseCase {
    return DeleteFavoriteByNameUseCase(favoritesRepository)
}

fun provideDeleteAllFavoritesUseCase(favoritesRepository: IFavoritesRepository): DeleteAllFavoritesBaseUseCase {
    return DeleteAllFavoritesUseCase(favoritesRepository)
}

fun provideGetAllFavoritesUseCase(favoritesRepository: IFavoritesRepository): GetAllFavoritesBaseUseCase {
    return GetAllFavoritesUseCase(favoritesRepository)
}

fun provideGetFavoriteByNameUseCase(favoritesRepository: IFavoritesRepository): GetFavoriteByNameBaseUseCase {
    return GetFavoriteByNameUseCase(favoritesRepository)
}

fun provideGetFavoriteByIdUseCase(favoritesRepository: IFavoritesRepository): GetFavoriteByIdBaseUseCase {
    return GetFavoriteByIdUseCase(favoritesRepository)
}

fun provideInsertFavoriteUseCase(favoritesRepository: IFavoritesRepository): InsertFavoriteBaseUseCase {
    return InsertFavoriteUseCase(favoritesRepository)
}
