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
package com.github.odaridavid.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.odaridavid.data.local.repository.FavoritesRepository
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
    fun `given favorite inputs when added to the db then return saved input`() =
        runBlocking(Dispatchers.IO) {

            //Save to db with insert transaction
            favoriteRepository.insertFavorite(SampleData.favorite).collect { result ->
                Truth.assertThat(result).isEqualTo("Done")
            }


            val index = 0
            //Retrieve saved values
            val favorites = favoriteRepository.getAllFavorites()
            favorites.collect { favs ->
                Truth.assertThat(favs).hasSize(1)
                with(favs[index]) {
                    Truth.assertThat(id).isEqualTo(1)
                    Truth.assertThat(films[index].title)
                        .isEqualTo(SampleData.favorite.films[index].title)
                }
            }

            //Clear data from tables
            db.clearAllTables()
        }

    @Test
    fun `given a single favorite id when deleted from db then return one affected row`() =
        runBlocking(Dispatchers.IO) {
            //Id of favorite to be deleted
            var favId: Int = 0

            //Save to db with insert transaction
            favoriteRepository.insertFavorite(SampleData.favorite).collect()

            //Assign id of created row
            favoriteRepository.getAllFavorites().collect { favs ->
                favId = favs[0].id
            }

            //Delete Item with specified id
            favoriteRepository.deleteFavoriteById(favId).collect { rowsAffected ->
                Truth.assertThat(rowsAffected).isAtMost(1)
            }

            //Verify Item Deleted
            favoriteRepository.getAllFavorites().collect { favs ->
                Truth.assertThat(favs).isEmpty()
            }

            //Clear data from tables
            db.clearAllTables()
        }

    @Test
    fun `given all entries must be deleted when deleted from db then return no of affected row`() =
        runBlocking(Dispatchers.IO) {

            //Save multiple values to db with insert transaction
            favoriteRepository.insertFavorite(SampleData.favorite).collect()
            favoriteRepository.insertFavorite(SampleData.favorite).collect()
            favoriteRepository.insertFavorite(SampleData.favorite).collect()
            favoriteRepository.insertFavorite(SampleData.favorite).collect()

            //Check no of favorites in db
            val favorites = favoriteRepository.getAllFavorites()
            favorites.collect { favs ->
                Truth.assertThat(favs).hasSize(4)
            }

            //Delete all favorites from db
            favoriteRepository.deleteAllFavorites().collect { rowsAffected ->
                Truth.assertThat(rowsAffected).isAtMost(4)
            }

            //Verify All Items Deleted
            favorites.collect { favs ->
                Truth.assertThat(favs).isEmpty()
            }

            //Clear data from tables
            db.clearAllTables()
        }

    @Test
    fun `given a favorite id when queried then return a specific favorite`() =
        runBlocking(Dispatchers.IO) {

            //Save values to db with insert transaction
            favoriteRepository.insertFavorite(SampleData.favorite).collect()

            //Check if fav exists using id
            val favorite = favoriteRepository.getFavoriteById(1)
            favorite.collect { favs ->
                Truth.assertThat(favs).isEqualTo(SampleData.favoriteWithDbIds)
            }

            //Clear data from tables
            db.clearAllTables()
        }

    @Test
    fun `given a favorite name when queried then return a specific favorite`() =
        runBlocking(Dispatchers.IO) {

            //Save values to db with insert transaction
            favoriteRepository.insertFavorite(SampleData.favorite).collect()

            //Check if fav exists using name
            val favorite = favoriteRepository.getFavoriteByName(SampleData.favorite.name)
            favorite.collect { favs ->
                Truth.assertThat(favs).isEqualTo(SampleData.favoriteWithDbIds)
            }

            //Clear data from tables
            db.clearAllTables()
        }

    @Test
    fun `given a favorite name when deleted then return no of affected rows`() =
        runBlocking(Dispatchers.IO) {
            //Save values to db with insert transaction
            favoriteRepository.insertFavorite(SampleData.favorite).collect()

            //Check if fav exists using name
            val favorite = favoriteRepository.deleteFavoriteByName(SampleData.favorite.name)
            favorite.collect { rowsAffected ->
                Truth.assertThat(rowsAffected).isEqualTo(1)
            }

            //Clear data from tables
            db.clearAllTables()
        }

}