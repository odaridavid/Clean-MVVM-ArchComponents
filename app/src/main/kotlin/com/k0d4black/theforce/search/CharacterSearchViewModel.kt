package com.k0d4black.theforce.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.domain.usecases.CharacterSearchUseCase
import kotlinx.coroutines.launch

class CharacterSearchViewModel(private val characterSearchUseCase: CharacterSearchUseCase) : ViewModel() {

    fun executeCharacterSearch() {
        viewModelScope.launch {
            characterSearchUseCase.searchCharacters("default")
        }
    }
}