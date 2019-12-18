package com.k0d4black.theforce.data.source

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.helpers.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_CHARACTER_ID
import com.k0d4black.theforce.data.helpers.NON_EXISTANT_CHARACTER_ID
import com.k0d4black.theforce.data.source.details.CharacterDetailsDataSource
import com.k0d4black.theforce.data.source.details.CharacterDetailsDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterDetailsDataSourceTest : BaseTest() {

    lateinit var characterDetailsDataSource: CharacterDetailsDataSource

    @Before
    override fun setup() {
        super.setup()
        characterDetailsDataSource = CharacterDetailsDataSourceImpl(starWarsApiService)
    }

    @Test
    fun `given character of interest when executed then get character details`() {
        runBlocking {
            val response = characterDetailsDataSource.getCharacter(EXISTING_CHARACTER_ID)
            Truth.assertThat(response?.name).matches("Luke Skywalker")
            Truth.assertThat(response?.films).isNotEmpty()
            Truth.assertThat(response?.species).isNotEmpty()
            Truth.assertThat(response?.homeworld).isNotNull()
        }
    }

    @Test
    fun `given invalid character when executed then return null value`() {
        runBlocking {
            val characterDetailsDataModel =
                characterDetailsDataSource.getCharacter(NON_EXISTANT_CHARACTER_ID)

            Truth.assertThat(characterDetailsDataModel).isNull()
        }
    }
}