package com.k0d4black.theforce.feature.charactersearchresults.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.feature.charactersearchresults.mappers.CharacterPresentationMapper
import com.k0d4black.theforce.feature.charactersearchresults.model.CharacterSearchResultViewState
import com.k0d4black.theforce.remote.charactersearch.data.CharacterSearchRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class CharacterSearchResultsViewModel(
    private val characterSearchRepository: com.k0d4black.theforce.remote.charactersearch.data.CharacterSearchRepository,
    private val characterPresentationMapper: CharacterPresentationMapper
) : ViewModel() {

    private val _characterSearchResultViewState = MutableLiveData<CharacterSearchResultViewState>()
    val characterSearchResultViewState: LiveData<CharacterSearchResultViewState>
        get() = _characterSearchResultViewState

    fun executeCharacterSearch(characterName: String) {
        val viewState = CharacterSearchResultViewState(isLoading = true)
        _characterSearchResultViewState.value = viewState

        viewModelScope.launch {

            characterSearchRepository.search(characterName).collect { characters ->

                val characterPresentations = characters.map { character ->
                    characterPresentationMapper.mapFromDomain(character = character)
                }

                _characterSearchResultViewState.value = viewState.copy(
                    isLoading = false,
                    characterSearchResultSearchResults = characterPresentations
                )
            }
        }

    }

}
