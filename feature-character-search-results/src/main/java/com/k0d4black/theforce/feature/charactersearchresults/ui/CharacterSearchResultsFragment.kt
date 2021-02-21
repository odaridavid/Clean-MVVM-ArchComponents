package com.k0d4black.theforce.feature.charactersearchresults.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.k0d4black.theforce.feature.charactersearchresults.databinding.FragmentCharacterSearchResultsBinding
import com.k0d4black.theforce.feature.charactersearchresults.model.CharacterSearchResultPresentation
import com.k0d4black.theforce.shared.android.base.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterSearchResultsFragment : BindingFragment<FragmentCharacterSearchResultsBinding>() {

    // region Members

    private val characterSearchResultsViewModel: CharacterSearchResultsViewModel by viewModel()

    // endregion

    // region Companion Object

    companion object {
        // TODO Have this pattern sorted out when I fix navigation
        const val CHARACTER_NAME = "key_character_name"
    }

    // endregion

    // region Binding Fragment

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCharacterSearchResultsBinding =
        FragmentCharacterSearchResultsBinding.inflate(inflater, container, false)

    // endregion

    // region Android Api

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
