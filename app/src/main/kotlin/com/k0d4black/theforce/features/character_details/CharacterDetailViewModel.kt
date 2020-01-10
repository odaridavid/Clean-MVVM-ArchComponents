package com.k0d4black.theforce.features.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.UiStateViewModel
import com.k0d4black.theforce.domain.usecases.GetCharacterBasicInfoUseCase
import com.k0d4black.theforce.domain.usecases.GetCharacterFilmsUseCase
import com.k0d4black.theforce.domain.usecases.GetCharacterPlanetUseCase
import com.k0d4black.theforce.domain.usecases.GetCharacterSpeciesUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.CharacterDetailsPresentationModel
import com.k0d4black.theforce.models.CharacterFilmPresentationModel
import com.k0d4black.theforce.models.CharacterPlanetPresentationModel
import com.k0d4black.theforce.models.CharacterSpeciesPresentationModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val getCharacterBasicInfoUseCase: GetCharacterBasicInfoUseCase,
    private val getCharacterSpeciesUseCase: GetCharacterSpeciesUseCase,
    private val getCharacterPlanetUseCase: GetCharacterPlanetUseCase,
    private val getCharacterFilmsUseCase: GetCharacterFilmsUseCase
) :
    UiStateViewModel() {

    val characterDetail: LiveData<CharacterDetailsPresentationModel>
        get() = _characterDetail

    private var _characterDetail =
        MutableLiveData<CharacterDetailsPresentationModel>()

    val characterCharacterPlanet: LiveData<CharacterPlanetPresentationModel>
        get() = _characterPlanet

    private var _characterPlanet =
        MutableLiveData<CharacterPlanetPresentationModel>()

    val characterFilms: LiveData<List<CharacterFilmPresentationModel>>
        get() = _characterFilms

    private var _characterFilms =
        MutableLiveData<List<CharacterFilmPresentationModel>>()

    val characterCharacterSpecies: LiveData<List<CharacterSpeciesPresentationModel>>
        get() = _characterSpecies

    private var _characterSpecies =
        MutableLiveData<List<CharacterSpeciesPresentationModel>>()

    fun getCharacterDetails(characterId: Int) {
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            getCharacterBasicInfoUseCase.execute(characterId).collect {
                _characterDetail.value = it.toPresentation()
            }
            getCharacterPlanetUseCase.execute(characterId).collect {
                _characterPlanet.value = it.toPresentation()
            }
            getCharacterFilmsUseCase.execute(characterId).collect {
                _characterFilms.value = it.map { film -> film.toPresentation() }
            }
            getCharacterSpeciesUseCase.execute(characterId).collect {
                _characterSpecies.value = it.map { species -> species.toPresentation() }
            }
            _uiState.value = Success
        }
    }
}