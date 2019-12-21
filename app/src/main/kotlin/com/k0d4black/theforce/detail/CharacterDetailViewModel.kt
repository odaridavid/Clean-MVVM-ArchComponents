package com.k0d4black.theforce.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.data.usecases.GetCharacterDetailsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase) :
    ViewModel() {

    fun getCharacterDetails() {
        viewModelScope.launch {
            getCharacterDetailsUseCase.getCharacterDetails(1)
        }
    }
}