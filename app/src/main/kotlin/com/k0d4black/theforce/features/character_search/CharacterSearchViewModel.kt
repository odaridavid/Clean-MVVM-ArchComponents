package com.k0d4black.theforce.features.character_search

import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.UiStateViewModel
import com.k0d4black.theforce.domain.usecases.SearchCharactersUseCase
import com.k0d4black.theforce.mappers.toPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterSearchViewModel @Inject constructor(
    private val searchCharactersUseCase: SearchCharactersUseCase
) : UiStateViewModel() {

    fun executeCharacterSearch(characterName: String) {
        viewModelScope.launch(handler) {
            _uiState.value = Loading
            searchCharactersUseCase(characterName).collect { results ->
                val characters = results.map { character -> character.toPresentation() }
                _uiState.value = Success(characters)
            }
        }
    }
}

