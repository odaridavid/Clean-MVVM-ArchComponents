package com.k0d4black.theforce.features.character_search

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.k0d4black.theforce.commons.ExceptionHandler
import com.k0d4black.theforce.domain.usecases.SearchUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.CharacterPresentation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class CharacterSearchViewModel(
    private val searchCharactersUseCase: SearchUseCase
) : ViewModel() {

    val searchViewState: LiveData<SearchViewState>
        get() = _searchViewState

    private var _searchViewState = MutableLiveData<SearchViewState>()

    private val searchExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _searchViewState.value = Error(message)
    }

    fun executeCharacterSearch(characterName: String) {
        viewModelScope.launch(searchExceptionHandler) {
            _searchViewState.value = Loading
            searchCharactersUseCase(characterName).collect { results ->
                val characters = results.map { character -> character.toPresentation() }
                _searchViewState.value = SearchResultLoaded(characters)
            }
        }
    }


}

internal sealed class SearchViewState
internal class Error(@StringRes val message: Int) : SearchViewState()
internal object Loading : SearchViewState()
internal class SearchResultLoaded(
    val searchResults: List<CharacterPresentation>
) : SearchViewState()


