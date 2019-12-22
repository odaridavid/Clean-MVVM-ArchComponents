package com.k0d4black.theforce.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.data.usecases.CharacterSearchUseCase
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.Loading
import com.k0d4black.theforce.domain.utils.ResultWrapper
import com.k0d4black.theforce.domain.utils.Success
import com.k0d4black.theforce.models.SearchedCharacterPresentationModel
import com.k0d4black.theforce.mappers.toPresentation
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterSearchViewModel @Inject constructor(
    private val characterSearchUseCase: CharacterSearchUseCase
) : ViewModel() {

    val searchResults: LiveData<ResultWrapper<List<SearchedCharacterPresentationModel>>>
        get() = _searchResults

    private var _searchResults: MutableLiveData<ResultWrapper<List<SearchedCharacterPresentationModel>>> =
        MutableLiveData()

    fun executeCharacterSearch(params: String) {
        _searchResults.postValue(Loading)
        viewModelScope.launch {
            when (val results = characterSearchUseCase.searchCharacters(params)) {
                is Success -> {
                    _searchResults.postValue(Success(results.data.map { it.toPresentation() }))
                }
                is Error -> _searchResults.postValue(Error(results.exception))
            }
        }
    }
}