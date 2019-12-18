package com.k0d4black.theforce.detail;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.k0d4black.theforce.domain.usecases.GetCharacterDetailsUseCase

class CharacterViewModelFactory(private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java))
            CharacterDetailViewModel(getCharacterDetailsUseCase) as T
        else throw IllegalStateException("Unknown ViewModel Class")
    }

}