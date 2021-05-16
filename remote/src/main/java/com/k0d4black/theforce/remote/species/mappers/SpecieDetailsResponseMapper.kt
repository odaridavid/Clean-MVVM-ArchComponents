package com.k0d4black.theforce.remote.species.mappers

import com.k0d4black.theforce.remote.species.models.SpecieDetailsResponse
import com.k0d4black.theforce.shared.species.Specie

class SpecieDetailsResponseMapper {

    fun mapToDomain(specieDetailsResponse: SpecieDetailsResponse): Specie =
        with(specieDetailsResponse) { Specie(name = name, language = language) }

}
