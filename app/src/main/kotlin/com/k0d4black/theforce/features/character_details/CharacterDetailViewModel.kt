package com.k0d4black.theforce.features.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.data.usecases.CharacterDetailsUseCase
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.Loading
import com.k0d4black.theforce.domain.utils.ResultWrapper
import com.k0d4black.theforce.domain.utils.Success
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.CharacterDetailsPresentationModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(private val characterDetailsUseCase: CharacterDetailsUseCase) :
    ViewModel() {

    //TODO Emit Data From Source as it streams in
    private val handler = CoroutineExceptionHandler { _, exception ->
        _characterDetail.postValue(Error(exception))
        println(exception.printStackTrace())
    }

    val characterDetail: LiveData<ResultWrapper<CharacterDetailsPresentationModel>>
        get() = _characterDetail

    private var _characterDetail =
        MutableLiveData<ResultWrapper<CharacterDetailsPresentationModel>>()

    fun getCharacterDetails(characterId: Int) {
        _characterDetail.value = Loading
        viewModelScope.launch(handler) {
            when (val results = characterDetailsUseCase.getCharacterDetails(characterId)) {
                is Success -> _characterDetail.postValue(Success(results.data.toPresentation()))
            }
        }
    }
}