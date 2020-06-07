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
package com.k0d4black.theforce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.ExceptionHandler
import com.k0d4black.theforce.domain.usecases.*
import com.k0d4black.theforce.mappers.toDomain
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.FavoritePresentation
import com.k0d4black.theforce.models.states.CharacterDetailsFavoriteViewState
import com.k0d4black.theforce.models.states.CharacterDetailsViewState
import com.k0d4black.theforce.models.states.Error
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class CharacterDetailViewModel(
    private val getSpeciesUseCase: GetSpeciesBaseUseCase,
    private val getPlanetUseCase: GetPlanetBaseUseCase,
    private val getFilmsUseCase: GetFilmsBaseUseCase,
    private val deleteFavoriteByNameUseCase: DeleteFavoriteByNameBaseUseCase,
    private val insertFavoriteUseCase: InsertFavoriteBaseUseCase,
    private val getFavoriteByNameUseCase: GetFavoriteByNameBaseUseCase
) : ViewModel() {

    val detailViewState: LiveData<CharacterDetailsViewState>
        get() = _detailViewState

    private var _detailViewState = MutableLiveData<CharacterDetailsViewState>()

    val detailFavoriteViewState: LiveData<CharacterDetailsFavoriteViewState>
        get() = _detailFavoriteViewState

    private var _detailFavoriteViewState = MutableLiveData<CharacterDetailsFavoriteViewState>()

    private val characterDetailExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _detailViewState.value = _detailViewState.value?.copy(error = Error(message))
    }

    private val characterDetailFavoriteExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            val message = ExceptionHandler.parse(exception)
            _detailFavoriteViewState.value =
                _detailFavoriteViewState.value?.copy(error = Error(message))
        }

    init {
        _detailViewState.value =
            CharacterDetailsViewState(
                isComplete = false,
                error = null,
                planet = null,
                films = null,
                specie = null,
                info = null
            )
        _detailFavoriteViewState.value =
            CharacterDetailsFavoriteViewState(isFavorite = false, error = null)
    }

    fun getCharacterDetails(characterUrl: String, isRetry: Boolean = false) {
        if (isRetry) {
            _detailViewState.value = _detailViewState.value?.copy(error = null)
        }
        viewModelScope.launch(characterDetailExceptionHandler) {
            async { loadPlanet(characterUrl) }.await()
            async { loadFilms(characterUrl) }.await()
            async { loadSpecies(characterUrl) }.await()
            _detailViewState.value = _detailViewState.value?.copy(isComplete = true)
        }
    }

    private suspend fun loadPlanet(characterUrl: String) {
        getPlanetUseCase(characterUrl).collect { planet ->
            val planetPresentation = planet.toPresentation()
            _detailViewState.value = _detailViewState.value?.copy(planet = planetPresentation)
        }
    }

    private suspend fun loadFilms(characterUrl: String) {
        getFilmsUseCase(characterUrl).collect { films ->
            val filmsPresentation = films.map { film -> film.toPresentation() }
            _detailViewState.value = _detailViewState.value?.copy(films = filmsPresentation)
        }
    }

    private suspend fun loadSpecies(characterUrl: String) {
        getSpeciesUseCase(characterUrl).collect { species ->
            val speciesPresentation = species.map { specie -> specie.toPresentation() }
            _detailViewState.value = _detailViewState.value?.copy(specie = speciesPresentation)
        }
    }

    fun getFavorite(name: String) {
        viewModelScope.launch(characterDetailFavoriteExceptionHandler) {
            getFavoriteByNameUseCase(name).collect { fav ->
                fav?.run {
                    _detailFavoriteViewState.value =
                        _detailFavoriteViewState.value?.copy(isFavorite = true)
                }
            }
        }
    }

    fun deleteFavorite(name: String) {
        viewModelScope.launch(characterDetailFavoriteExceptionHandler) {
            deleteFavoriteByNameUseCase(name).collect { row ->
                if (row == 1) {
                    _detailFavoriteViewState.value =
                        _detailFavoriteViewState.value?.copy(isFavorite = false)
                }
            }
        }
    }

    fun saveFavorite(favoritePresentation: FavoritePresentation) {
        viewModelScope.launch(characterDetailFavoriteExceptionHandler) {
            insertFavoriteUseCase(favoritePresentation.toDomain()).collect { result ->
                if (result.contentEquals("Done")) {
                    _detailFavoriteViewState.value =
                        _detailFavoriteViewState.value?.copy(isFavorite = true)
                }
            }
        }
    }

    fun displayCharacterError(message: Int) {
        _detailViewState.value = _detailViewState.value?.copy(error = Error(message))
    }

}




