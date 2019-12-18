package com.k0d4black.theforce.data.source.utils

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.models.entities.SpeciesDataModel
import com.k0d4black.theforce.domain.utils.id

suspend fun populateSpeciesList(
    species: MutableList<SpeciesDataModel>,
    speciesUrls: List<String>,
    apiService: StarWarsApiService
) {
    for (specie in speciesUrls) {
        val specieResponse = apiService.getSpecies(specie.id)
        val speciesDataModel = SpeciesDataModel(specieResponse.language)
        species.add(speciesDataModel)
    }
}