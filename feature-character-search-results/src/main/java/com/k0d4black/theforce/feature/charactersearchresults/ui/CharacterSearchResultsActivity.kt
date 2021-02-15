package com.k0d4black.theforce.feature.charactersearchresults.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.k0d4black.theforce.feature.charactersearchresults.R
import com.k0d4black.theforce.feature.charactersearchresults.databinding.ActivityCharacterSearchResultsBinding
import com.k0d4black.theforce.feature.charactersearchresults.model.CharacterSearchResultPresentation
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterSearchResultsActivity : AppCompatActivity() {

    // region Members

    private val characterSearchResultsViewModel: CharacterSearchResultsViewModel by viewModel()
    private lateinit var binding: ActivityCharacterSearchResultsBinding

    // endregion

    // region Companion Object

    companion object {
        // TODO Have this pattern sorted out when I fix navigation
        const val CHARACTER_NAME = "key_character_name"
    }

    // endregion

    // region Android Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterSearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val characterName = savedInstanceState?.getString(CHARACTER_NAME) ?: ""
        observeViewState()
        characterSearchResultsViewModel.executeCharacterSearch(characterName = characterName)
    }

    // endregion

    // region Private Api

    private fun observeViewState() {
        characterSearchResultsViewModel.characterSearchResultViewState.observe(this) { characterSearchResultViewState ->
            renderLoading(isLoading = characterSearchResultViewState.isLoading)

            renderError()

            renderResults(searchResults = characterSearchResultViewState.characterSearchResultSearchResults)
        }
    }

    private fun renderLoading(isLoading: Boolean) {

    }

    private fun renderError() {

    }

    private fun renderResults(searchResults: List<CharacterSearchResultPresentation>?) {

    }

    // endregion
}
