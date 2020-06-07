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
package com.k0d4black.theforce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.k0d4black.theforce.commons.ExceptionHandler
import com.k0d4black.theforce.domain.usecases.DeleteAllFavoritesBaseUseCase
import com.k0d4black.theforce.domain.usecases.DeleteFavoriteByNameBaseUseCase
import com.k0d4black.theforce.domain.usecases.GetAllFavoritesBaseUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.FavoritePresentation
import com.k0d4black.theforce.models.states.Error
import com.k0d4black.theforce.models.states.DashboardFavoritesViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


internal class DashboardFavoritesViewModel(
    private val deleteFavoriteByNameUseCase: DeleteFavoriteByNameBaseUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesBaseUseCase,
    private val deleteAllFavoritesUseCase: DeleteAllFavoritesBaseUseCase
) : ViewModel() {

    val dashboardFavoritesViewState: LiveData<DashboardFavoritesViewState>
        get() = _favoriteViewState

    private var _favoriteViewState = MutableLiveData<DashboardFavoritesViewState>()

    private val favoritesExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _favoriteViewState.value =
            _favoriteViewState.value?.copy(isLoading = false, error = Error(message))
    }

    init {
        _favoriteViewState.value =
            DashboardFavoritesViewState(isLoading = true, error = null, favorites = null)
        getAllFavorites()
    }

    private fun onFavsLoading() {
        _favoriteViewState.value = _favoriteViewState.value?.copy(isLoading = true)
    }

    private fun onFavsLoadingComplete(favs: List<FavoritePresentation>) {
        _favoriteViewState.value =
            _favoriteViewState.value?.copy(isLoading = false, favorites = favs)
    }


    fun getAllFavorites() {
        viewModelScope.launch(favoritesExceptionHandler) {
            onFavsLoading()
            loadFavorites()
        }
    }

    private suspend fun loadFavorites() {
        getAllFavoritesUseCase(Unit).collect { favorites ->
            val favs = favorites.map { it.toPresentation() }
            onFavsLoadingComplete(favs)
        }
    }

    fun deleteAllFavorites() {
        viewModelScope.launch(favoritesExceptionHandler) {
            deleteAllFavoritesUseCase(Unit).collect { rows ->
                if (rows > 0) {
                    _favoriteViewState.value =
                        _favoriteViewState.value?.copy(isLoading = false, favorites = emptyList())
                }
            }
        }
    }

    fun deleteFavorite(name: String) {
        viewModelScope.launch(favoritesExceptionHandler) {
            deleteFavoriteByNameUseCase(name).collect { row ->
                if (row > 0) {
                    loadFavorites()
                }
            }
        }
    }

}