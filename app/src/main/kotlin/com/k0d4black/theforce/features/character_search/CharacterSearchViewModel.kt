package com.k0d4black.theforce.features.character_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.UiStateViewModel
import com.k0d4black.theforce.data.usecases.CharacterSearchUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.CharacterSearchPresentationModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterSearchViewModel @Inject constructor(
    private val characterSearchUseCase: CharacterSearchUseCase
) : UiStateViewModel() {

    val searchResults: LiveData<List<CharacterSearchPresentationModel>>
        get() = _searchResults

    private var _searchResults: MutableLiveData<List<CharacterSearchPresentationModel>> =
        MutableLiveData()

    fun executeCharacterSearch(params: String) {
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            characterSearchUseCase.execute(params).collect { results ->
                _searchResults.value = results.map { it.toPresentation() }
            }
            _uiState.value = Success
        }
    }
}
