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
package com.github.odaridavid.local.core

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.odaridavid.local.favorites.repository.FavoritesRepository
import com.google.common.truth.Truth
import com.k0d4black.theforce.domain.repository.IFavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
internal class FavoritesRepositoryTest : BaseTest() {

    private lateinit var favoriteRepository: IFavoritesRepository

    @Before
    override fun setup() {
        super.setup()
        favoriteRepository = FavoritesRepository(favoritesDao = favoritesDao)
    }

    @Test
    fun `when all favorites are requested , then return all saved favorites`() =
            runBlocking(Dispatchers.IO) {

                // Given we save a favorite item to the db
                favoriteRepository.insertFavorite(SampleData.favorite).collect()

                // When we get all favorite items
                val favorites = favoriteRepository.getAllFavorites()

                // Then get saved favorite items
                favorites.collect { favs ->
                    Truth.assertThat(favs).isEqualTo(listOf(SampleData.favorite))
                }
            }


    @Test
    fun `when we delete all favorites,then return an empty list of favorites`() =
            runBlocking(Dispatchers.IO) {

                // Given we save multiple favorites to the database
                favoriteRepository.insertFavorite(SampleData.favorite).collect()
                favoriteRepository.insertFavorite(SampleData.favorite).collect()
                favoriteRepository.insertFavorite(SampleData.favorite).collect()
                favoriteRepository.insertFavorite(SampleData.favorite).collect()

                // When we delete all favorites from db
                favoriteRepository.deleteAllFavorites().collect()

                // Then assert all favorites were deleted
                favoriteRepository.getAllFavorites().collect { favs ->
                    Truth.assertThat(favs).isEmpty()
                }
            }


    //TODO Use JunitParams to assert negative path
    @Test
    fun `when queried for a specific favorite,then return the specific favorite`() =
            runBlocking(Dispatchers.IO) {

                // Given we save a favorite
                favoriteRepository.insertFavorite(SampleData.favorite).collect()

                // When we query for that specific favorite
                val favorite = favoriteRepository.getFavoriteByName(SampleData.favorite.name)

                // Then assert that favorite exists
                favorite.collect { favs ->
                    Truth.assertThat(favs).isEqualTo(SampleData.favorite)
                }
            }

    @Test
    fun `when a specific favorite is deleted, then no of affected rows should be one`() =
            runBlocking(Dispatchers.IO) {

                // Given we save a favorite
                favoriteRepository.insertFavorite(SampleData.favorite).collect()

                // When we delete that specific favorite
                val favorite = favoriteRepository.deleteFavoriteByName(SampleData.favorite.name)

                // Then assert no of deleted rows was one
                favorite.collect { rowsAffected ->
                    Truth.assertThat(rowsAffected).isEqualTo(1)
                }

            }

}
