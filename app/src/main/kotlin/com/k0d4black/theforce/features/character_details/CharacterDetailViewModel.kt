package com.k0d4black.theforce.features.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.Error
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.UiStateViewModel
import com.k0d4black.theforce.domain.usecases.GetFilmsUseCase
import com.k0d4black.theforce.domain.usecases.GetPlanetUseCase
import com.k0d4black.theforce.domain.usecases.GetSpeciesUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.FilmPresentation
import com.k0d4black.theforce.models.PlanetPresentation
import com.k0d4black.theforce.models.SpeciePresentation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val getPlanetUseCase: GetPlanetUseCase,
    private val getFilmsUseCase: GetFilmsUseCase
) : UiStateViewModel() {

    val characterPlanet: LiveData<PlanetPresentation>
        get() = _characterPlanet

    private var _characterPlanet =
        MutableLiveData<PlanetPresentation>()

    val starWarsCharacterFilms: LiveData<List<FilmPresentation>>
        get() = _characterFilms

    private var _characterFilms =
        MutableLiveData<List<FilmPresentation>>()

    val characterStarWarsCharacterSpecies: LiveData<List<SpeciePresentation>>
        get() = _characterSpecies

    private var _characterSpecies =
        MutableLiveData<List<SpeciePresentation>>()

    fun getCharacterDetails(characterUrl: String) {
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            getPlanetUseCase(characterUrl).collect {
                _characterPlanet.value = it.toPresentation()
            }
            getFilmsUseCase(characterUrl).collect {
                _characterFilms.value = it.map { film -> film.toPresentation() }
            }
            getSpeciesUseCase(characterUrl).collect {
                _characterSpecies.value = it.map { species -> species.toPresentation() }
            }
            _uiState.value = Success(Unit)
        }
    }

    fun displayCharacterError() {
        _uiState.value = Error(Exception("Error Loading Character"))
    }
}