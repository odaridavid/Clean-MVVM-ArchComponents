package com.k0d4black.theforce.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.domain.usecases.GetCharacterDetailsUseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase) :
    ViewModel() {

    fun getCharacterDetails() {
        viewModelScope.launch {
            getCharacterDetailsUseCase.getCharacterDetails(1)
        }
    }
}