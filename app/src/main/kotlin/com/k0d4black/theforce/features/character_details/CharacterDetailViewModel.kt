package com.k0d4black.theforce.features.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.Error
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.UiStateViewModel
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterFilmsUseCase
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterPlanetUseCase
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterSpeciesUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.StarWarsCharacterFilmsUiModel
import com.k0d4black.theforce.models.StarWarsCharacterPlanetUiModel
import com.k0d4black.theforce.models.StarWarsCharacterSpeciesUiModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val getStarWarsCharacterSpeciesUseCase: GetStarWarsCharacterSpeciesUseCase,
    private val getStarWarsCharacterPlanetUseCase: GetStarWarsCharacterPlanetUseCase,
    private val getStarWarsCharacterFilmsUseCase: GetStarWarsCharacterFilmsUseCase
) : UiStateViewModel() {

    val characterStarWarsCharacterPlanet: LiveData<StarWarsCharacterPlanetUiModel>
        get() = _characterPlanet

    private var _characterPlanet =
        MutableLiveData<StarWarsCharacterPlanetUiModel>()

    val starWarsCharacterFilms: LiveData<List<StarWarsCharacterFilmsUiModel>>
        get() = _characterFilms

    private var _characterFilms =
        MutableLiveData<List<StarWarsCharacterFilmsUiModel>>()

    val characterStarWarsCharacterSpecies: LiveData<List<StarWarsCharacterSpeciesUiModel>>
        get() = _characterSpecies

    private var _characterSpecies =
        MutableLiveData<List<StarWarsCharacterSpeciesUiModel>>()

    fun getCharacterDetails(characterId: Int) {
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            getStarWarsCharacterPlanetUseCase(characterId).collect {
                _characterPlanet.value = it.toPresentation()
            }
            getStarWarsCharacterFilmsUseCase(characterId).collect {
                _characterFilms.value = it.map { film -> film.toPresentation() }
            }
            getStarWarsCharacterSpeciesUseCase(characterId).collect {
                _characterSpecies.value = it.map { species -> species.toPresentation() }
            }
            _uiState.value = Success
        }
    }

    fun displayCharacterError() {
        //TODO Navigate back to search
        _uiState.value = Error(Exception("Error Loading Character"))
    }
}