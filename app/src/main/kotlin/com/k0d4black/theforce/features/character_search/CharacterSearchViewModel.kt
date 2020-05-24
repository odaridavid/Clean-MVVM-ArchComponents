package com.k0d4black.theforce.features.character_search

import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.UiStateViewModel
import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.usecases.BaseUseCase
import com.k0d4black.theforce.domain.usecases.SearchCharactersUseCase
import com.k0d4black.theforce.domain.usecases.SearchUseCase
import com.k0d4black.theforce.mappers.toPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterSearchViewModel(
    private val searchCharactersUseCase: SearchUseCase
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

