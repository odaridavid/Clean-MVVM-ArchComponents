package com.k0d4black.theforce.data.source

import com.google.common.truth.Truth
import com.k0d4black.theforce.data.BaseTest
import com.k0d4black.theforce.data.helpers.EXISTING_CHARACTER_ID
import com.k0d4black.theforce.data.helpers.NON_EXISTANT_CHARACTER_ID
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.Success
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class CharacterDetailsDataSourceTest : BaseTest() {

    private lateinit var characterDetailsDataSource: CharacterDetailsDataSource

    @Before
    override fun setup() {
        super.setup()
        characterDetailsDataSource =
            CharacterDetailsDataSource(
                starWarsApiService
            )
    }

    @Test
    fun `given a valid character id when executed then return character details`() {
        runBlocking {
            val characterDetailsDataModel =
                characterDetailsDataSource.getCharacter(EXISTING_CHARACTER_ID)
            Truth.assertThat(characterDetailsDataModel).isInstanceOf(Success::class.java)
        }
    }

    @Test
    fun `given invalid character id when executed then return error response `() {
        runBlocking {
            val characterDetailsDataModel =
                characterDetailsDataSource.getCharacter(NON_EXISTANT_CHARACTER_ID)

            Truth.assertThat(characterDetailsDataModel).isInstanceOf(Error::class.java)
        }
    }
}