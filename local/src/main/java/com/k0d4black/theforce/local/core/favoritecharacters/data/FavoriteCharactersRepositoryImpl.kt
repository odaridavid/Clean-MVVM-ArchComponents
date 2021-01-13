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
package com.k0d4black.theforce.local.core.favoritecharacters.data

import com.k0d4black.theforce.local.core.favoritecharacters.mappers.FavoriteCharacterMapper
import com.k0d4black.theforce.shared.model.FavoriteCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteCharactersRepositoryImpl(
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val favoriteCharacterMapper: FavoriteCharacterMapper
) : FavoriteCharactersRepository {

    override fun getAllFavorites(): Flow<List<FavoriteCharacter>> = flow {
        val favoriteCharactersEntities = favoriteCharactersDao.getAll()
        val favoriteCharacters = favoriteCharactersEntities.map { favoriteCharacterEntity ->
            favoriteCharacterMapper.toDomain(favoriteCharacterEntity = favoriteCharacterEntity)
        }
        emit(favoriteCharacters)
    }

    override fun getFavoriteByName(name: String): Flow<FavoriteCharacter> = flow {
        val favoriteCharacterEntity = favoriteCharactersDao.getByName(name = name)
        val favoriteCharacter = favoriteCharacterMapper.toDomain(
            favoriteCharacterEntity = favoriteCharacterEntity
        )
        emit(value = favoriteCharacter)
    }

    override fun deleteFavoriteByName(name: String): Flow<Int> = flow {
        val rowsAffected = favoriteCharactersDao.deleteByName(name = name)
        emit(value = rowsAffected)
    }

    override fun deleteAllFavorites(): Flow<Int> = flow {
        val rowsAffected = favoriteCharactersDao.deleteAll()
        emit(value = rowsAffected)
    }

    override fun insertFavorite(favoriteCharacter: FavoriteCharacter): Flow<Long> = flow {
        val favoriteCharacterEntity = favoriteCharacterMapper.toDbEntity(
            favoriteCharacter = favoriteCharacter
        )
        val rowsAffected = favoriteCharactersDao.insert(
            favoriteCharacterEntity = favoriteCharacterEntity
        )
        emit(value = rowsAffected)
    }
}