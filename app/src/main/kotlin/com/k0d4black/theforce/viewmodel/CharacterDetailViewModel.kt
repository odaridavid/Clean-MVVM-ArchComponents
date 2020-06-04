package com.k0d4black.theforce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k0d4black.theforce.commons.ExceptionHandler
import com.k0d4black.theforce.domain.models.Favorite
import com.k0d4black.theforce.domain.usecases.*
import com.k0d4black.theforce.mappers.toDomain
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.FavoritePresentation
import com.k0d4black.theforce.models.PlanetPresentation
import com.k0d4black.theforce.models.SpeciePresentation
import com.k0d4black.theforce.models.states.CharacterDetailsViewState
import com.k0d4black.theforce.models.states.Error
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class CharacterDetailViewModel(
    private val getSpeciesUseCase: GetSpeciesBaseUseCase,
    private val getPlanetUseCase: GetPlanetBaseUseCase,
    private val getFilmsUseCase: GetFilmsBaseUseCase,
    private val deleteFavoriteByNameUseCase: DeleteFavoriteByNameBaseUseCase,
    private val insertFavoriteUseCase: InsertFavoriteBaseUseCase,
    private val getFavoriteByNameUseCase: GetFavoriteByNameBaseUseCase
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
                isFavorite = false,
                isComplete = false,
                error = null,
                planet = null,
                films = null,
                specie = null,
                info = null
            )
    }

    fun getCharacterDetails(characterUrl: String, isRetry: Boolean = false) {
        if (isRetry) {
            _detailViewState.value = _detailViewState.value?.copy(error = null)
        }
        viewModelScope.launch(characterDetailExceptionHandler) {
            async { loadPlanet(characterUrl) }.await()
            async { loadFilms(characterUrl) }.await()
            async { loadSpecies(characterUrl) }.await()
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

    fun deleteFavorite(name: String) {
        viewModelScope.launch(characterDetailExceptionHandler) {
            deleteFavoriteByNameUseCase(name).collect { row ->
                if (row == 1) {
                    _detailViewState.value = _detailViewState.value?.copy(isFavorite = false)
                }
            }
        }
    }

    fun getFavorite(name: String) {
        viewModelScope.launch(characterDetailExceptionHandler) {
            getFavoriteByNameUseCase(name).collect { favorite ->
                favoriteToPresentation(favorite)
            }
        }
    }

    fun saveFavorite(favoritePresentation: FavoritePresentation) {
        viewModelScope.launch(characterDetailExceptionHandler) {
            insertFavoriteUseCase(favoritePresentation.toDomain()).collect { result ->
                if (result.contentEquals("Done")) {
                    _detailViewState.value = _detailViewState.value?.copy(isFavorite = true)
                }
            }
        }
    }

    fun displayCharacterError(message: Int) {
        _detailViewState.value = _detailViewState.value?.copy(error = Error(message))
    }

    private fun favoriteToPresentation(favorite: Favorite) {
        //TODO Move to mappers
        val favoritePresentation = favorite.toPresentation()
        val planet = PlanetPresentation(
            favoritePresentation.planetName,
            favoritePresentation.planetPopulation
        )
        val specie = SpeciePresentation(
            favoritePresentation.specieName,
            favoritePresentation.specieLanguage
        )
        val films = favoritePresentation.films
        val info = CharacterPresentation(
            favoritePresentation.name,
            favoritePresentation.birthYear,
            favoritePresentation.height,
            favoritePresentation.heightInInches,
            favoritePresentation.id.toString()
        )

        _detailViewState.value = _detailViewState.value?.copy(
            isComplete = true,
            isFavorite = true,
            planet = planet,
            specie = listOf(specie),
            films = films,
            info = info
        )
    }
}




