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
package com.k0d4black.theforce.feature.character.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.k0d4black.theforce.shared.android.ExceptionHandler
import com.k0d4black.theforce.domain.usecases.GetFilmsBaseUseCase
import com.k0d4black.theforce.domain.usecases.GetPlanetBaseUseCase
import com.k0d4black.theforce.domain.usecases.GetSpeciesBaseUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.FavoritePresentation
import com.k0d4black.theforce.models.states.CharacterDetailsViewState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

internal class CharacterDetailViewModel(
    private val getSpeciesUseCase: GetSpeciesBaseUseCase,
    private val getPlanetUseCase: GetPlanetBaseUseCase,
    private val getFilmsUseCase: GetFilmsBaseUseCase
) : com.k0d4black.theforce.shared.android.base.BaseViewModel() {

    // region Members

    private var characterDetailsJob: Job? = null

    val detailViewState: LiveData<CharacterDetailsViewState>
        get() = _detailViewState

    private var _detailViewState = MutableLiveData<CharacterDetailsViewState>()

    val remoteToFavoritePresentation: LiveData<FavoritePresentation>
        get() = _remoteToFavoritePresentation

    private var _remoteToFavoritePresentation = MutableLiveData<FavoritePresentation>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = com.k0d4black.theforce.shared.android.ExceptionHandler.parse(exception)
        _detailViewState.value = _detailViewState.value?.copy(error = Error(message))
    }

    // endregion

    // region Constructor

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
    }

    // endregion

    // region Android API

    override fun onCleared() {
        super.onCleared()
        characterDetailsJob?.cancel()
    }

    // endregion

    // region Public API

    fun initView(character: CharacterPresentation) {
        _detailViewState.value = _detailViewState.value?.copy(info = character)
    }

    fun getCharacterDetails(characterUrl: String, isRetry: Boolean = false) {
        if (isRetry) {
            _detailViewState.value = _detailViewState.value?.copy(error = null)
        }
        characterDetailsJob = launchCoroutine {
            async { loadPlanet(characterUrl) }.await()
            async { loadFilms(characterUrl) }.await()
            async { loadSpecies(characterUrl) }.await()
            _detailViewState.value = _detailViewState.value?.copy(isComplete = true)
        }
    }

    fun displayCharacterError(message: Int) {
        _detailViewState.value = _detailViewState.value?.copy(error = Error(message))
    }

    fun createFavoritePresentationFromRemoteCharacter() {
        val character = _detailViewState.value?.info ?: return
        val planet = _detailViewState.value?.planet ?: return
        val films = _detailViewState.value?.films ?: return
        val species = _detailViewState.value?.specie ?: return
        val favoritePresentation = FavoritePresentation(
            characterPresentation = character,
            planetPresentation = planet,
            speciePresentation = species,
            films = films
        )
        _remoteToFavoritePresentation.value = favoritePresentation
    }

    // endregion

    // region Private API

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

    // endregion
}
