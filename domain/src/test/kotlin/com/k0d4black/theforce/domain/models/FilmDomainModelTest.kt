package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test

internal class FilmDomainModelTest {

    lateinit var filmDomainModel: FilmDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val openingCrawl = "Mwaha ha this is the opening crawl for this film mwa ha ha."

        filmDomainModel = FilmDomainModel(openingCrawl)

        //Then
        Truth.assertThat(filmDomainModel.openingCrawl).matches(openingCrawl)
    }
}