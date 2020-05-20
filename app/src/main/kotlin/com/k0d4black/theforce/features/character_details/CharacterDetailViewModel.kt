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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val getPlanetUseCase: GetPlanetUseCase,
    private val getFilmsUseCase: GetFilmsUseCase
) : UiStateViewModel() {

    val planet: LiveData<PlanetPresentation>
        get() = _planet

    private var _planet = MutableLiveData<PlanetPresentation>()

    val films: LiveData<List<FilmPresentation>>
        get() = _films

    private var _films = MutableLiveData<List<FilmPresentation>>()

    val species: LiveData<List<SpeciePresentation>>
        get() = _species

    private var _species = MutableLiveData<List<SpeciePresentation>>()

    fun getCharacterDetails(characterUrl: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _uiState.postValue(Loading)
            loadPlanet(characterUrl)
            loadFilms(characterUrl)
            loadSpecies(characterUrl)
            _uiState.postValue(Success(Unit))
        }
    }

    private suspend fun loadPlanet(characterUrl: String) {
        getPlanetUseCase(characterUrl).collect { planet ->
            val planetPresentation = planet.toPresentation()
            _planet.postValue(planetPresentation)
        }
    }

    private suspend fun loadFilms(characterUrl: String) {
        getFilmsUseCase(characterUrl).collect { films ->
            val filmsPresentation = films.map { eachFilm -> eachFilm.toPresentation() }
            _films.postValue(filmsPresentation)
        }
    }

    private suspend fun loadSpecies(characterUrl: String) {
        getSpeciesUseCase(characterUrl).collect { species ->
            val speciesPresentation = species.map { eachSpecie -> eachSpecie.toPresentation() }
            _species.postValue(speciesPresentation)
        }
    }

    fun displayCharacterError() {
        _uiState.value = Error(Exception("Error Loading Character"))
    }
}