package com.k0d4black.theforce.models.states

import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.FilmPresentation
import com.k0d4black.theforce.models.PlanetPresentation
import com.k0d4black.theforce.models.SpeciePresentation

internal data class CharacterDetailsViewState(
    val isFavorite: Boolean,
    val isComplete: Boolean,
    val error: Error?,
    val planet: PlanetPresentation?,
    val films: List<FilmPresentation>?,
    val specie: List<SpeciePresentation>?,
    val info: CharacterPresentation?
)