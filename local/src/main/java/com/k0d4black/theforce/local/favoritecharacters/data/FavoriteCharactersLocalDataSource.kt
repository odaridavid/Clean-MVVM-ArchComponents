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
package com.k0d4black.theforce.local.favoritecharacters.data

import com.k0d4black.theforce.local.favoritecharacters.mappers.FavoriteCharacterMapper
import com.k0d4black.theforce.shared.characters.Character
import com.k0d4black.theforce.shared.characters.FavoriteCharactersDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteCharactersLocalDataSource(
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val favoriteCharacterMapper: FavoriteCharacterMapper
) : FavoriteCharactersDataSource {

    override fun getAllFavorites(): Flow<List<Character>> = flow {
        val favoriteCharactersEntities = favoriteCharactersDao.getAll()
        val favoriteCharacters = favoriteCharactersEntities.map { favoriteCharacterEntity ->
            favoriteCharacterMapper.mapToDomain(favoriteCharacterEntity = favoriteCharacterEntity)
        }
        emit(favoriteCharacters)
    }

    override fun getFavoriteByName(name: String): Flow<Character> = flow {
        val favoriteCharacterEntity = favoriteCharactersDao.getByName(name = name)
        val favoriteCharacter = favoriteCharacterMapper.mapToDomain(
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

    override fun insertFavorite(favoriteCharacter: Character): Flow<Long> = flow {
        val favoriteCharacterEntity = favoriteCharacterMapper.mapToDbEntity(
            favoriteCharacter = favoriteCharacter
        )
        val rowsAffected = favoriteCharactersDao.insert(
            favoriteCharacterEntity = favoriteCharacterEntity
        )
        emit(value = rowsAffected)
    }
}
