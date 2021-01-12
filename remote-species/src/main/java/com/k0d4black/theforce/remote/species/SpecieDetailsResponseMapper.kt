package com.k0d4black.theforce.remote.species

import com.k0d4black.theforce.shared.model.Specie

class SpecieDetailsResponseMapper {

    fun toDomain(specieDetailsResponse: SpecieDetailsResponse): Specie =
        with(specieDetailsResponse) { Specie(name = name, language = language) }

}
