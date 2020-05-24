package com.k0d4black.theforce.features.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.Error
import com.k0d4black.theforce.commons.Loading
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.UiStateViewModel
import com.k0d4black.theforce.domain.usecases.FilmsUseCase
import com.k0d4black.theforce.domain.usecases.PlanetUseCase
import com.k0d4black.theforce.domain.usecases.SpeciesUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.FilmPresentation
import com.k0d4black.theforce.models.PlanetPresentation
import com.k0d4black.theforce.models.SpeciePresentation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val getSpeciesUseCase: SpeciesUseCase,
    private val getPlanetUseCase: PlanetUseCase,
    private val getFilmsUseCase: FilmsUseCase
) : UiStateViewModel() {

    val planet: LiveData<PlanetPresentation>
        get() = _planet

    private var _planet = MutableLiveData<PlanetPresentation>()

    val films: LiveData<FilmPresentation>
        get() = _films

    private var _films = MutableLiveData<FilmPresentation>()

    val species: LiveData<List<SpeciePresentation>>
        get() = _species

    private var _species = MutableLiveData<List<SpeciePresentation>>()

    fun getCharacterDetails(characterUrl: String) {
        viewModelScope.launch(handler) {
            _uiState.value = Loading
            loadPlanet(characterUrl)
            loadFilms(characterUrl)
            loadSpecies(characterUrl)
            _uiState.value = Success(Unit)
        }
    }

    private suspend fun loadPlanet(characterUrl: String) {
        getPlanetUseCase(characterUrl).collect { planet ->
            val planetPresentation = planet.toPresentation()
            _planet.value = planetPresentation
        }
    }

    private suspend fun loadFilms(characterUrl: String) {
        getFilmsUseCase(characterUrl).collect { films ->
            val filmsPresentation = films.toPresentation()
            _films.value = filmsPresentation
        }
    }

    private suspend fun loadSpecies(characterUrl: String) {
        getSpeciesUseCase(characterUrl).collect { species ->
            val speciesPresentation = species.map { eachSpecie -> eachSpecie.toPresentation() }
            _species.value = speciesPresentation
        }
    }

    fun displayCharacterError() {
        _uiState.value = Error(Exception("Error Loading Character"))
    }
}