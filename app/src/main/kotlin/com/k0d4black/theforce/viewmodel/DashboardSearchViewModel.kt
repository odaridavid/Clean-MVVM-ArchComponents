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
import com.k0d4black.theforce.domain.usecases.SearchCharactersBaseUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.states.Error
import com.k0d4black.theforce.models.states.DashboardSearchViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class DashboardSearchViewModel(
    private val searchCharactersUseCase: SearchCharactersBaseUseCase
) : ViewModel() {

    private var searchJob: Job? = null

    val dashboardSearchViewState: LiveData<DashboardSearchViewState>
        get() = _searchViewState

    private var _searchViewState = MutableLiveData<DashboardSearchViewState>()

    init {
        _searchViewState.value =
            DashboardSearchViewState(isLoading = false, error = null, searchResults = null)
    }

    private val searchExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        onSearchError(message)
    }

    fun executeCharacterSearch(characterName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(searchExceptionHandler) {
            delay(500)
            onSearchLoading()
            searchCharactersUseCase(characterName).collect { results ->
                val characters = results.map { character -> character.toPresentation() }
                onSearchComplete(characters)
            }
        }
    }

    private fun onSearchComplete(characters: List<CharacterPresentation>) {
        _searchViewState.value =
            _searchViewState.value?.copy(isLoading = false, searchResults = characters)
    }

    private fun onSearchLoading() {
        _searchViewState.value = _searchViewState.value?.copy(isLoading = true)
    }

    private fun onSearchError(message: Int) {
        _searchViewState.value =
            _searchViewState.value?.copy(isLoading = false, error = Error(message))
    }

}



