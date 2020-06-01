package com.k0d4black.theforce.features.character_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.ExceptionHandler
import com.k0d4black.theforce.domain.usecases.SearchUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.Error
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class CharacterSearchViewModel(
    private val searchCharactersUseCase: SearchUseCase
) : ViewModel() {

    private var searchJob: Job? = null

    val searchViewState: LiveData<SearchResultViewState>
        get() = _searchViewState

    private var _searchViewState = MutableLiveData<SearchResultViewState>()

    private val searchExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _searchViewState.value =
            _searchViewState.value?.copy(isLoading = false, error = Error(message))
    }

    init {
        _searchViewState.value =
            SearchResultViewState(isLoading = false, searchResults = null, error = null)
    }

    fun executeCharacterSearch(characterName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(searchExceptionHandler) {
            delay(500)
            _searchViewState.value = _searchViewState.value?.copy(isLoading = true)
            searchCharactersUseCase(characterName).collect { results ->
                val characters = results.map { character -> character.toPresentation() }
                _searchViewState.value =
                    _searchViewState.value?.copy(isLoading = false, searchResults = characters)
            }
        }
    }
}

internal data class SearchResultViewState(
    val isLoading: Boolean,
    val error: Error?,
    val searchResults: List<CharacterPresentation>?
)


