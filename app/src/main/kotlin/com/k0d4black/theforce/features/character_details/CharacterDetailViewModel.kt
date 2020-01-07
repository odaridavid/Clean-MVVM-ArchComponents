package com.k0d4black.theforce.features.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.Error
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.UiStateViewModel
import com.k0d4black.theforce.data.usecases.CharacterDetailsUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.CharacterDetailsPresentationModel
import com.k0d4black.theforce.models.FilmPresentationModel
import com.k0d4black.theforce.models.PlanetPresentationModel
import com.k0d4black.theforce.models.SpeciesPresentationModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(private val characterDetailsUseCase: CharacterDetailsUseCase) :
    UiStateViewModel() {

    val characterDetail: LiveData<CharacterDetailsPresentationModel>
        get() = _characterDetail

    private var _characterDetail =
        MutableLiveData<CharacterDetailsPresentationModel>()

    val characterPlanet: LiveData<PlanetPresentationModel>
        get() = _characterPlanet

    private var _characterPlanet =
        MutableLiveData<PlanetPresentationModel>()

    val characterFilms: LiveData<List<FilmPresentationModel>>
        get() = _characterFilms

    private var _characterFilms =
        MutableLiveData<List<FilmPresentationModel>>()

    val characterSpecies: LiveData<List<SpeciesPresentationModel>>
        get() = _characterSpecies

    private var _characterSpecies =
        MutableLiveData<List<SpeciesPresentationModel>>()

    fun getCharacterDetails(characterId: Int) {
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            characterDetailsUseCase.getCharacterBasicDetails(characterId).collect {
                _characterDetail.value = it.toPresentation()
            }
            characterDetailsUseCase.getCharacterPlanet(characterId).collect {
                _characterPlanet.value = it.toPresentation()
            }
            characterDetailsUseCase.getCharacterFilms(characterId).collect {
                _characterFilms.value = it.map { film -> film.toPresentation() }
            }
            characterDetailsUseCase.getCharacterSpecies(characterId).collect {
                _characterSpecies.value = it.map { species -> species.toPresentation() }
            }
            _uiState.value = Success
        }
    }
}