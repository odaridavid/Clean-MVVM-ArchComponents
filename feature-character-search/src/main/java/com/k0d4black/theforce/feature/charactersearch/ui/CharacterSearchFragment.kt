package com.k0d4black.theforce.feature.charactersearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.k0d4black.theforce.feature.charactersearch.databinding.FragmentCharacterSearchBinding
import com.k0d4black.theforce.feature.charactersearch.model.RecentSearchPresentation
import com.k0d4black.theforce.shared.android.base.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterSearchFragment : BindingFragment<FragmentCharacterSearchBinding>() {

    // region Members

    private val characterSearchViewModel: CharacterSearchViewModel by viewModel()

    // endregion

    // region Binding Fragment

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCharacterSearchBinding =
        FragmentCharacterSearchBinding.inflate(layoutInflater, container, false)

    // endregion

    // region Private Api

    private fun bindEvents() {
        // TODO On click of search navigate to search results page
    }

    private fun observeViewState() {
        characterSearchViewModel.searchViewState.observe(this) { characterSearchViewState ->
            renderRecentSearchLoading(
                isLoading = characterSearchViewState.isRecentSearchLoading
            )

            renderError()

            renderRecentSearch(
                recentSearch = characterSearchViewState.recentSearches
            )
        }
    }

    private fun renderRecentSearchLoading(isLoading: Boolean) {

    }

    private fun renderError() {

    }

    private fun renderRecentSearch(recentSearch: List<RecentSearchPresentation>?) {

    }


    // endregion

}
