package com.k0d4black.theforce.features.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.ExceptionHandler
import com.k0d4black.theforce.domain.usecases.FilmsUseCase
import com.k0d4black.theforce.domain.usecases.PlanetUseCase
import com.k0d4black.theforce.domain.usecases.SpeciesUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.FilmPresentation
import com.k0d4black.theforce.models.PlanetPresentation
import com.k0d4black.theforce.models.SpeciePresentation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class CharacterDetailViewModel(
    private val getSpeciesUseCase: SpeciesUseCase,
    private val getPlanetUseCase: PlanetUseCase,
    private val getFilmsUseCase: FilmsUseCase
) : ViewModel() {

    val detailViewState: LiveData<CharacterDetailsViewState>
        get() = _detailViewState

    private var _detailViewState = MutableLiveData<CharacterDetailsViewState>()

    private val characterDetailExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _detailViewState.value = _detailViewState.value?.copy(error = Error(message),isLoading = false)
    }

    init {
        _detailViewState.value =
            CharacterDetailsViewState(
                isLoading = true,
                error = null,
                planet = null,
                films = null,
                specie = null
            )
    }

    fun getCharacterDetails(characterUrl: String) {
        viewModelScope.launch(characterDetailExceptionHandler) {
            loadPlanet(characterUrl)
            loadFilms(characterUrl)
            loadSpecies(characterUrl)
            _detailViewState.value = _detailViewState.value?.copy(isLoading = false)
        }
    }

    private suspend fun loadPlanet(characterUrl: String) {
        getPlanetUseCase(characterUrl).collect { planet ->
            val planetPresentation = planet.toPresentation()
            _detailViewState.value = _detailViewState.value?.copy(planet = planetPresentation)
        }
    }

    private suspend fun loadFilms(characterUrl: String) {
        getFilmsUseCase(characterUrl).collect { films ->
            val filmsPresentation = films.map { film -> film.toPresentation() }
            _detailViewState.value = _detailViewState.value?.copy(films = filmsPresentation)
        }
    }

    private suspend fun loadSpecies(characterUrl: String) {
        getSpeciesUseCase(characterUrl).collect { species ->
            val speciesPresentation = species.map { specie -> specie.toPresentation() }
            _detailViewState.value = _detailViewState.value?.copy(specie = speciesPresentation)
        }
    }

    fun displayCharacterError() {
        _detailViewState.value =
            _detailViewState.value?.copy(error = Error("Couldn't load character details."),isLoading = false)
    }
}

internal sealed class DetailViewState
internal data class Error(val message: String) : DetailViewState()
internal data class CharacterDetailsViewState(
    val isLoading: Boolean = false,
    val error: Error?,
    val planet: PlanetPresentation?,
    val films: List<FilmPresentation>?,
    val specie: List<SpeciePresentation>?
) : DetailViewState()


