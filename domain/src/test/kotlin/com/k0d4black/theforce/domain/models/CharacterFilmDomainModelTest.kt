package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test

internal class CharacterFilmDomainModelTest {

    lateinit var expectedModel: CharacterFilmDomainModel

    @Test
    fun `instantiate character film domain model`() {
        //Given
        expectedModel =
            CharacterFilmDomainModel(
                title = "Film Title",
                openingCrawl = "Mwaha ha this is the opening crawl for this film mwa ha ha."
            )
        val actualModel = expectedModel.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(CharacterFilmDomainModel::class.java)
        Truth.assertThat(actualModel).isEqualTo(expectedModel)
    }
}