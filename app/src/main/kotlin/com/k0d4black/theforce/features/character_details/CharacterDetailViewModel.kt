package com.k0d4black.theforce.features.character_details

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.k0d4black.theforce.commons.ExceptionHandler
import com.k0d4black.theforce.domain.usecases.FilmsUseCase
import com.k0d4black.theforce.domain.usecases.PlanetUseCase
import com.k0d4black.theforce.domain.usecases.SpeciesUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.FilmPresentation
import com.k0d4black.theforce.models.PlanetPresentation
import com.k0d4black.theforce.models.SpeciePresentation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
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
        _detailViewState.value = _detailViewState.value?.copy(error = Error(message))
    }

    init {
        _detailViewState.value =
            CharacterDetailsViewState(
                isComplete = false,
                error = null,
                planet = null,
                films = null,
                specie = null
            )
    }

    fun getCharacterDetails(characterUrl: String, isRetry: Boolean = false) {
        if (isRetry) {
            _detailViewState.value = _detailViewState.value?.copy(error = null)
        }
        viewModelScope.launch(characterDetailExceptionHandler) {
            val planetRequest = async { loadPlanet(characterUrl) }
            val filmsRequest = async { loadFilms(characterUrl) }
            val speciesRequest = async { loadSpecies(characterUrl) }
            planetRequest.await()
            filmsRequest.await()
            speciesRequest.await()
            _detailViewState.value = _detailViewState.value?.copy(isComplete = true)

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

    fun displayCharacterError(message: Int) {
        _detailViewState.value =
            _detailViewState.value?.copy(error = Error(message))
    }
}

internal sealed class DetailViewState
internal data class Error(@StringRes val message: Int) : DetailViewState()
internal data class CharacterDetailsViewState(
    val isComplete: Boolean,
    val error: Error?,
    val planet: PlanetPresentation?,
    val films: List<FilmPresentation>?,
    val specie: List<SpeciePresentation>?
) : DetailViewState()


