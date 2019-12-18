package com.k0d4black.theforce.search;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.k0d4black.theforce.domain.usecases.CharacterSearchUseCase

class CharacterSearchViewModelFactory(private val characterSearchUseCase: CharacterSearchUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharacterSearchViewModel::class.java))
            CharacterSearchViewModel(characterSearchUseCase) as T
        else throw IllegalStateException("Unknown ViewModel Class")
    }

}