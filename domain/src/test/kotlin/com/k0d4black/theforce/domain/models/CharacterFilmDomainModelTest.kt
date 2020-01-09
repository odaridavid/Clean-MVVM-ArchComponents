package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import com.k0d4black.theforce.domain.CharacterFilmDomainModel
import org.junit.Test

internal class CharacterFilmDomainModelTest {

    lateinit var characterFilmDomainModel: CharacterFilmDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val openingCrawl = "Mwaha ha this is the opening crawl for this film mwa ha ha."
        val title = "Film Title"

        characterFilmDomainModel =
            CharacterFilmDomainModel(title, openingCrawl)

        //Then
        Truth.assertThat(characterFilmDomainModel.openingCrawl).matches(openingCrawl)
    }
}