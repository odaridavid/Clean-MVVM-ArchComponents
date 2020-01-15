package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test

internal class StarWarsCharacterFilmTest {

    lateinit var expectedModelStarWars: StarWarsCharacterFilm

    @Test
    fun `instantiate character film domain model`() {
        //Given
        expectedModelStarWars =
            StarWarsCharacterFilm(
                title = "Film Title",
                openingCrawl = "Mwaha ha this is the opening crawl for this film mwa ha ha."
            )
        val actualModel = expectedModelStarWars.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(StarWarsCharacterFilm::class.java)
        Truth.assertThat(actualModel).isEqualTo(expectedModelStarWars)
    }
}