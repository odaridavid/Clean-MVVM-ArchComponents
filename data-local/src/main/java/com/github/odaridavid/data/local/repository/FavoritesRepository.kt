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
package com.github.odaridavid.data.local.repository

import com.github.odaridavid.data.local.dao.FavoritesDao
import com.github.odaridavid.data.local.mappers.toDomain
import com.k0d4black.theforce.domain.models.Favorite
import com.k0d4black.theforce.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FavoritesRepository(private val favoritesDao: FavoritesDao) : IFavoritesRepository {

    override fun getAllFavorites(): Flow<List<Favorite>> = flow {
        val favs = favoritesDao.getAll()
        emit(favs.map { it.toDomain() })
    }

    override fun getFavoriteById(id: Int): Flow<Favorite> = flow {
        val fav = favoritesDao.getById(id)
        emit(fav.toDomain())
    }

    override fun getFavoriteByName(name: String): Flow<Favorite> = flow {
        val fav = favoritesDao.getByName(name)
        emit(fav.toDomain())
    }

    override fun deleteFavorite(id: Int): Flow<Int> = flow {
        val rowsAffected = favoritesDao.delete(id)
        emit(rowsAffected)
    }

    override fun deleteAllFavorites(): Flow<Int> = flow {
        val rowsAffected = favoritesDao.deleteAll()
        emit(rowsAffected)
    }

    override fun insertFavorite(favorite: Favorite): Flow<String> = flow {
        favoritesDao.insert(favorite)
        emit("Done")
    }

}