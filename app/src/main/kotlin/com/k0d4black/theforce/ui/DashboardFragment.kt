/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.k0d4black.theforce.ui

import android.os.Bundle
import android.view.*
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.k0d4black.theforce.R
import com.k0d4black.theforce.adapters.createFavoritesAdapter
import com.k0d4black.theforce.adapters.createSearchResultAdapter
import com.k0d4black.theforce.base.BaseFragment
import com.k0d4black.theforce.commons.*
import com.k0d4black.theforce.idlingresource.EspressoIdlingResource
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.FavoritePresentation
import com.k0d4black.theforce.models.states.DashboardFavoritesViewState
import com.k0d4black.theforce.models.states.DashboardSearchViewState
import com.k0d4black.theforce.viewmodel.DashboardFavoritesViewModel
import com.k0d4black.theforce.viewmodel.DashboardSearchViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {

    // region Members

    private val characterSearchViewModel by viewModel<DashboardSearchViewModel>()
    private val favoritesViewModel by viewModel<DashboardFavoritesViewModel>()

    private val searchResultAdapter = createSearchResultAdapter {
        findNavController().navigate(DashboardFragmentDirections.actionToCharacterDetailsFragment(it))
    }

    private val favoritesAdapter = createFavoritesAdapter {
        findNavController().navigate(DashboardFragmentDirections.actionToFavoriteDetailsFragment(it))
    }

    // endregion

    // region Android API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configSupportActionBar()

        observeSearchViewState()
        observeFavoritesViewState()

        onInitialEditTextClick()
        handleTextChanges()
        handleUpButtonClick()
    }

    override fun onResume() {
        super.onResume()
        if (dashboard_layout.currentState == dashboard_layout.startState)
            favoritesViewModel.getAllFavorites()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            findNavController().navigate(DashboardFragmentDirections.actionToSettingsFragment())
            true
        } else super.onOptionsItemSelected(item)
    }

    // endregion

    // region Private API

    private fun configSupportActionBar() {
        (requireActivity() as DashboardActivity).setSupportActionBar(search_toolbar)
        (requireActivity() as DashboardActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun onInitialEditTextClick() {
        search_edit_text.setOnFocusChangeListener { _, _ ->
            dashboard_layout.transitionToEnd()
        }
    }

    private fun handleTextChanges() {
        search_edit_text.doOnTextChanged { text, _, _, _ ->
            text?.let { name ->
                if (name.length >= 2) {
                    dashboard_layout.transitionToEnd()
                    characterSearchViewModel.executeCharacterSearch(name.toString())
                }
            }
        }
    }

    private fun handleUpButtonClick() {
        back_to_start_state_image_button.setOnClickListener {
            dashboard_layout.transitionToStart()
        }
    }

    private fun observeFavoritesViewState() {
        favoritesViewModel.favoritesViewState.observe(viewLifecycleOwner, Observer { state ->

            handleFavoritesLoading(state)

            state.favorites?.let { favorites ->
                if (favorites.isNotEmpty()) {
                    handleFavorites(favorites)
                } else {
                    no_favorites_text_view.show()
                }
            }

            handleFavoritesError(state)
        })
    }

    private fun observeSearchViewState() {
        characterSearchViewModel.searchViewState.observe(viewLifecycleOwner, Observer { state ->

            handleSearchLoading(state)

            state.searchResults?.let { searchResults ->
                if (searchResults.isEmpty()) {
                    handleNoSearchResults()
                    return@let
                }
                handleSearchResults(searchResults)
            }

            handleSearchError(state)

        })
    }

    private fun handleSearchLoading(state: DashboardSearchViewState) {
        EspressoIdlingResource.decrement()
        if (state.isLoading) {
            search_results_recycler_view.hide()
            search_results_progress_bar.show()
        } else {
            search_results_progress_bar.hide()
            search_results_recycler_view.show()
        }
    }

    private fun handleFavoritesLoading(state: DashboardFavoritesViewState) {
        EspressoIdlingResource.decrement()
        if (state.isLoading) {
            favorites_progress_bar.show()
        } else {
            favorites_progress_bar.hide()
        }
    }

    private fun handleSearchResults(searchResults: List<CharacterPresentation>) {
        EspressoIdlingResource.decrement()
        showSnackbar(
            search_results_recycler_view,
            getString(R.string.info_search_done)
        )
        search_results_recycler_view.apply {
            adapter = ScaleInAnimationAdapter(searchResultAdapter.apply {
                submitList(searchResults)
                EspressoIdlingResource.decrement()
            })
            initRecyclerViewWithLineDecoration(requireContext())
        }
    }

    private fun handleFavorites(favorites: List<FavoritePresentation>) {
        no_favorites_text_view.hide()
        favorites_recycler_view.show()
        favorites_recycler_view.apply {
            adapter = ScaleInAnimationAdapter(favoritesAdapter.apply {
                submitList(favorites)
                EspressoIdlingResource.decrement()
            })
        }
    }

    private fun handleNoSearchResults() {
        EspressoIdlingResource.decrement()
        search_results_recycler_view.hide()
        showSnackbar(
            search_results_recycler_view,
            getString(R.string.info_no_results)
        )
    }

    private fun handleSearchError(state: DashboardSearchViewState) {
        EspressoIdlingResource.decrement()
        state.error?.run {
            showSnackbar(
                search_results_recycler_view,
                getString(this.message),
                isError = true
            )
        }
    }

    private fun handleFavoritesError(state: DashboardFavoritesViewState) {
        EspressoIdlingResource.decrement()
        state.error?.run {
            showSnackbar(
                favorites_recycler_view,
                getString(this.message),
                isError = true
            )
        }
    }

    // endregion
}
